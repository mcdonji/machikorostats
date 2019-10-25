package com.mcdonji.machikorostats.domain;

import com.mcdonji.machikorostats.domain.strategies.RandomStrategy;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Player {

    private UUID id;
    private int playerNumber;
    private String name;
    private Random random;
    private int money;
    private int totalRevenue;
    private int moves;
    private Strategy strategy;
    private ArrayList<Player> otherPlayers = new ArrayList<Player>();
    private ArrayList<Establishment> establishments = new ArrayList<Establishment>();


    private Landmark trainStation = Landmark.TrainStation();
    private Landmark shoppingMall = Landmark.ShoppingMall();
    private Landmark amusementPark = Landmark.AmusementPark();
    private Landmark radioTower = Landmark.RadioTower();

    public Player(int playerNumber, String name, Random random, int money, ArrayList<Establishment> initialEstablishments, Strategy strategy)    {
    	this(UUID.randomUUID(), playerNumber, name, random, money, initialEstablishments, strategy);
    }

    public Player(int playerNumber, String name, Random random, int money, ArrayList<Establishment> initialEstablishments)    {
    	this(UUID.randomUUID(), playerNumber, name, random, money, initialEstablishments, new RandomStrategy());
    }

    public Player(int playerNumber, Random random, int money, ArrayList<Establishment> initialEstablishments, Strategy strategy)    {
	    this(UUID.randomUUID(),playerNumber, "Player$(playerNumber)", random, money, initialEstablishments, strategy);
    }

    public Player(int playerNumber, Random random, int money, ArrayList<Establishment> initialEstablishments)    {
	    this(UUID.randomUUID(),playerNumber, "Player$(playerNumber)", random, money, initialEstablishments, new RandomStrategy());
    }
    public Player(int playerNumber, String name) {
        ArrayList<Establishment> initEstablishments = new ArrayList<>();
        initEstablishments.add(Establishments.WheatField);
        initEstablishments.add(Establishments.Bakery);
        this.id = UUID.randomUUID();
        this.playerNumber = playerNumber;
        this.name = name;
        this.random = new Random();
        this.money = 3;
        this.totalRevenue = 0;
        this.moves = 0;
        establishments = initEstablishments;
        this.strategy = new RandomStrategy();
    }

    public Player(UUID id, int playerNumber, String name, Random random, int money, ArrayList<Establishment> initialEstablishments, Strategy strategy)
    {
        this.id = id;
        this.playerNumber = playerNumber;
	    this.name = name;
        this.random = random;
        this.money = money;
        this.totalRevenue = 0;
        this.moves = 0;
        establishments = initialEstablishments;
        this.strategy = strategy;
    }

    public String compactRepresentation() {
        NumberFormat nf = new DecimalFormat("##.0");
        return String.format("(%s %s %s %s%s%s%s %s)", playerNumber, money, establishments.size(),  trainStation.isActive()?"A":"_", shoppingMall.isActive()?"A":"_", amusementPark.isActive()?"A":"_", radioTower.isActive()?"A":"_", nf.format( totalRevenue/moves));
    }



    public Collection<Establishment>  Establishments() {
        return establishments;
    }
    public int Money() {
        return this.money;
    }

    public void addOtherPlayers(ArrayList<Player> allPlayers) {
        for (Player otherPlayer:allPlayers) {
            if (!id.equals(otherPlayer.getId())) {
                otherPlayers.add(otherPlayer);
            }
        }
    }

    public int otherPlayerCount() 
    {
	return otherPlayers.size();
    }

    public UUID getId() {
        return id;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public String getName() {return name;    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public ArrayList<Landmark> landmarks() {
        ArrayList<Landmark> landmarks = new ArrayList<>();
        landmarks.add(trainStation);
        landmarks.add(shoppingMall);
        landmarks.add(amusementPark);
        landmarks.add(radioTower);
        return landmarks;
    }

    public EstablishmentDeck move(EstablishmentDeck deck) {
        DiceRoll roll = Dice.Roll(strategy.NumberOfDiceToRoll(this));
        if (canReroll() && strategy.shouldReroll(roll)) {
            roll = Dice.Roll(strategy.NumberOfDiceToRollOnRerole(this));
        }
        return move(deck, roll);
    }

    public EstablishmentDeck move(EstablishmentDeck deck, DiceRoll roll) {
        this.moves++;
        Map<UUID, Integer> revenuesByPlayer = CalculateRevenueForRoll(roll);
        this.money += revenuesByPlayer.get(id);
        this.totalRevenue += revenuesByPlayer.get(id);
        for (Player otherPlayer: otherPlayers) {
            if (revenuesByPlayer.containsKey(otherPlayer.getId())) {
                otherPlayer.money += revenuesByPlayer.get(otherPlayer.getId());
                otherPlayer.totalRevenue += revenuesByPlayer.get(otherPlayer.getId());
            }
        }
        for (Establishment tradeEnabledEstablishments: getTradeEnabledEstablishments()) {
            if (Arrays.stream(tradeEnabledEstablishments.getActivateOnRole()).anyMatch(ao->ao ==roll.getValue())) {
                if (tradeEnabledEstablishments.getProductionOnType().equals(ProductionOnType.YourTurnTradeEstablishment)) {
                    EstablishmentTrade establishmentTrade = strategy.ChoosePlayerAndEstablishmentToTakeAndGive(this, otherPlayers);
                    trade(establishmentTrade.getToGive(), establishmentTrade.getToTake());
                    establishmentTrade.getOtherPlayer().trade(establishmentTrade.getToTake(), establishmentTrade.getToGive());
                }
            }
        }

        Landmark landmark = strategy.landmarkToActivate(this.money, landmarks());
        if (landmark != null) {
            activateLandmark(landmark);
        } else {
            Establishment desired = strategy.GetEstablishmentPreference(this.money, deck, this, otherPlayers);
            Establishment take = deck.Take(desired);
            if (take != null) {
                establishments.add(take);
                this.money -= deck.CostOf(take);
            }
        }
        return deck;
    }

    private void trade(Establishment toGive, Establishment toTake) {
        establishments.remove(toGive);
        establishments.add(toTake);
    }


    private boolean canReroll() {
        return radioTower.isActive();
    }

    public Map<UUID, Integer> CalculateRevenueForRoll(DiceRoll roll) {
        Map<UUID, Integer> revenuesByPlayer = new HashMap<UUID, Integer>();
        revenuesByPlayer.put(getId(), 0);
        for (Player otherPlayer: otherPlayers) revenuesByPlayer.put(otherPlayer.getId(), 0);

        revenuesByPlayer = CombineMaps(revenuesByPlayer, redRevenues(roll));
        revenuesByPlayer = CombineMaps(revenuesByPlayer, greenAndBlueRevenues(roll));
        revenuesByPlayer = CombineMaps(revenuesByPlayer, purpleRevenues(roll));
        return revenuesByPlayer;
    }

    private Map<UUID, Integer> CombineMaps(Map<UUID, Integer> map1, Map<UUID, Integer> map2) {
        return Stream.of(map1, map2).flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Player::add));
    }

    public static int add(int a, int b) {
        return a + b;
    }

    private HashMap<UUID, Integer> purpleRevenues(DiceRoll roll) {
        HashMap<UUID, Integer> revenuesByPlayer = new HashMap<>();
        for (Establishment purpleEstablishment: getPurpleEstablishments()) {
            if (Arrays.stream(purpleEstablishment.getActivateOnRole()).anyMatch(ao->ao ==roll.getValue())) {
                if (purpleEstablishment.getProductionOnType().equals(ProductionOnType.YourTurnFromAllPlayers)) {
                    int production = purpleEstablishment.getProduction(landmarks(), establishments);
                    for (Player otherPlayer: otherPlayers) {
                        takeProduction(revenuesByPlayer, production, otherPlayer);
                    }
                }
                if (purpleEstablishment.getProductionOnType().equals(ProductionOnType.YourTurnFromAnyChosenPlayer)) {
                    int production = purpleEstablishment.getProduction(landmarks(), establishments);
                    Player otherPlayer = strategy.ChoosePlayerToTakeFrom(this, otherPlayers);
                    takeProduction(revenuesByPlayer, production, otherPlayer);
                }
            }
        }
        return revenuesByPlayer;
    }

    private void takeProduction(HashMap<UUID, Integer> revenuesByPlayer, int production, Player otherPlayer) {
        if (revenuesByPlayer.containsKey(otherPlayer.getId())) {
            revenuesByPlayer.replace(otherPlayer.getId(), revenuesByPlayer.get(otherPlayer.getId()) - production);
        } else {
            revenuesByPlayer.put(otherPlayer.getId(), -production);
        }
        if (revenuesByPlayer.containsKey(getId())) {
            revenuesByPlayer.replace(getId(), revenuesByPlayer.get(getId()) + production);
        } else {
            revenuesByPlayer.put(getId(), production);
        }
    }

    private HashMap<UUID, Integer> greenAndBlueRevenues(DiceRoll roll) {
        HashMap<UUID, Integer> revenuesByPlayer = new HashMap<>();
        for (Establishment greenOrBlueEstablishment: getGreenAndBlueEstablishments()) {
            if (Arrays.stream(greenOrBlueEstablishment.getActivateOnRole()).anyMatch(ao->ao ==roll.getValue())) {
                if (greenOrBlueEstablishment.getProductionOnType().equals(ProductionOnType.YourTurn) ||
                        greenOrBlueEstablishment.getProductionOnType().equals(ProductionOnType.AnyonesTurn)) {
                    int production = greenOrBlueEstablishment.getProduction(landmarks(), establishments);
                    if (revenuesByPlayer.containsKey(getId())) {
                        revenuesByPlayer.replace(getId(), revenuesByPlayer.get(getId()) + production);
                    } else {
                        revenuesByPlayer.put(getId(), production);
                    }
                }
            }
        }
        for (Player otherPlayer: otherPlayers) {
            for (Establishment greenOrBlueEstablishment: otherPlayer.getGreenAndBlueEstablishments()) {
                if (Arrays.stream(greenOrBlueEstablishment.getActivateOnRole()).anyMatch(ao->ao == roll.getValue())) {
                    if (greenOrBlueEstablishment.getProductionOnType().equals(ProductionOnType.AnyonesTurn)) {
                        int production = greenOrBlueEstablishment.getProduction(landmarks(), establishments);
                        if (revenuesByPlayer.containsKey(otherPlayer.getId())) {
                            revenuesByPlayer.replace(otherPlayer.getId(), revenuesByPlayer.get(otherPlayer.getId()) + production);
                        } else {
                            revenuesByPlayer.put(otherPlayer.getId(), production);
                        }
                    }
                }
            }
        }
        return revenuesByPlayer;
    }


    private HashMap<UUID, Integer> redRevenues(DiceRoll roll) {
        HashMap<UUID, Integer> revenuesByPlayer = new HashMap<>();
        for (Player otherPlayer: otherPlayers) {
            for (Establishment redEstablishment: otherPlayer.getRedEstablishments()) {
                if (Arrays.stream(redEstablishment.getActivateOnRole()).anyMatch(ao->ao==roll.getValue())) {
                    if (redEstablishment.getProductionOnType().equals(ProductionOnType.FromPlayerWhoRolledTheDice)) {
                        int production = redEstablishment.getProduction(landmarks(), establishments);
                        if (revenuesByPlayer.containsKey(otherPlayer.getId())) {
                            revenuesByPlayer.replace(otherPlayer.getId(), revenuesByPlayer.get(otherPlayer.getId()) + production);
                        } else {
                            revenuesByPlayer.put(otherPlayer.getId(), production);
                        }
                        if (revenuesByPlayer.containsKey(getId())) {
                            revenuesByPlayer.replace(getId(), revenuesByPlayer.get(getId()) - production);
                        } else {
                            revenuesByPlayer.put(getId(), -production);
                        }
                    }
                }
            }
        }

        return revenuesByPlayer;
    }

    public List<Establishment> getRedEstablishments() {
        return establishments.stream().filter(es-> es.isRed()).collect(Collectors.toList());
    }

    public List<Establishment> getGreenAndBlueEstablishments() {
        return establishments.stream().filter(es-> es.isGreenOrBlue()).collect(Collectors.toList());
    }

    public List<Establishment> getNotPurpleEstablishments() {
        return establishments.stream().filter(es-> es.isNotPurple()).collect(Collectors.toList());
    }

    public List<Establishment> getPurpleEstablishments() {
        return establishments.stream().filter(es-> es.isPurple()).collect(Collectors.toList());
    }

    public List<Establishment> getTradeEnabledEstablishments() {
        return establishments.stream().filter(es-> es.isTradeEstablishment()).collect(Collectors.toList());
    }


    private void activateLandmark(Landmark building) {
        if (building.getName() == trainStation.getName()) {trainStation.setActive(true);}
        if (building.getName() == shoppingMall.getName()) {shoppingMall.setActive(true);}
        if (building.getName() == amusementPark.getName()) {amusementPark.setActive(true);}
        if (building.getName() == radioTower.getName()) {radioTower.setActive(true);}
    }

    private int roll() {
        return random.nextInt(6);
    }

    public boolean hasWon() {
        for (Landmark landmark: landmarks()) {
            if (landmark.isActive() == false) {
                return false;
            }
        }
        return true;
    }

    public int getMoney() {
        return money;
    }

    public int canRoll() {
        if (trainStation.isActive()) {
            return 2;
        };
        return 1;
    }

}
