package com.mcdonji.machikorostats.domain;

import java.util.*;


public class Player {

    private UUID id;
    private int playerNumber;
    private String name;
    private Random random;
    private int money;
    private Strategy strategy;
    private Collection<Player> otherPlayers = new ArrayList<Player>();
    private Collection<Establishment> establishments = new ArrayList<Establishment>();

    private Landmark trainStation = Landmark.TrainStation();
    private Landmark shoppingMall = Landmark.ShoppingMall();
    private Landmark amusementPark = Landmark.AmusementPark();
    private Landmark radioTower = Landmark.RadioTower();

    public Player(int playerNumber, String name, Random random, int money, Collection<Establishment> initialEstablishments, Strategy strategy)    {
    	this(UUID.randomUUID(), playerNumber, name, random, money, initialEstablishments, strategy);
    }

    public Player(int playerNumber, String name, Random random, int money, Collection<Establishment> initialEstablishments)    {
    	this(UUID.randomUUID(), playerNumber, name, random, money, initialEstablishments, new SimpleStrategy());
    }

    public Player(int playerNumber, Random random, int money, Collection<Establishment> initialEstablishments, Strategy strategy)    {
	    this(UUID.randomUUID(),playerNumber, "Player$(playerNumber)", random, money, initialEstablishments, strategy);
    }

    public Player(int playerNumber, Random random, int money, Collection<Establishment> initialEstablishments)    {
	    this(UUID.randomUUID(),playerNumber, "Player$(playerNumber)", random, money, initialEstablishments, new SimpleStrategy());
    }

    public Player(UUID id, int playerNumber,  String name, Random random, int money, Collection<Establishment> initialEstablishments, Strategy strategy)
    {
        this.id = id;
        this.playerNumber = playerNumber;
	    this.name = name;
        this.random = random;
        this.money = money;
        establishments = initialEstablishments;
        this.strategy = strategy;
    }


    public void getEstablishment(Establishment establishment) {
        establishments.add(establishment);
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

    public String getName() {
        return name;
    }

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

    public List<Landmark> Landmarks() {
        ArrayList<Landmark> landmarks = new ArrayList<>();
        landmarks.add(trainStation);
        landmarks.add(shoppingMall);
        landmarks.add(amusementPark);
        landmarks.add(radioTower);
        return landmarks;
    }

    public EstablishmentDeck Move(EstablishmentDeck deck) {
        DiceRoll roll = Dice.Roll(strategy.NumberOfDiceToRoll(this));
        if (canReroll() && strategy.shouldReroll(roll)) {
            roll = Dice.Roll(strategy.NumberOfDiceToRollOnRerole(this));
        }
        return Move(deck, roll);
    }

    public EstablishmentDeck Move(EstablishmentDeck deck, DiceRoll roll) {
        money += CalculateRevenueForRoll(roll);
        if (strategy.shouldActivateLandmark(money, Landmarks())) {
            Landmark landmark = strategy.landmarkToActivate(money, Landmarks());
            activateLandmark(landmark);
        } else {
            Establishment desired = strategy.GetEstablishmentPreference(money, deck, this, otherPlayers);
            Establishment take = deck.Take(desired);
            if (take != null) {
                establishments.add(take);
                money -= deck.CostOf(take);
            }
        }
        return deck;
    }

    private boolean canReroll() {
        return radioTower.isActive();
    }

    public int CalculateRevenueForRoll(DiceRoll roll) {
        int calculatedRevenue = 0;
        int revenueFromMe = revenueFromMyRoll(roll);
        calculatedRevenue += revenueFromMe;
        int revenueFromOthers = 0;
        for (Player otherPlayer: otherPlayers) {
            revenueFromOthers += otherPlayer.calculateRevenueFromOtherPlayersRoll(roll);
        }
        calculatedRevenue += revenueFromOthers;
        return calculatedRevenue;
    }

    private void activateLandmark(Landmark building) {
        if (building.getName() == trainStation.getName()) {trainStation.setActive(true);}
        if (building.getName() == shoppingMall.getName()) {shoppingMall.setActive(true);}
        if (building.getName() == amusementPark.getName()) {amusementPark.setActive(true);}
        if (building.getName() == radioTower.getName()) {radioTower.setActive(true);}
    }

    private boolean shouldActivateBuilding() {
        return false;
    }

    private int calculateRevenueFromOtherPlayersRoll(DiceRoll roll) {
        int revenue = 0;
        for (Establishment establishment: establishments) {

        }
        return revenue;
    }

    public int revenueFromMyRoll(DiceRoll roll) {
        int revenue = 0;
        for (Establishment establishment: establishments) {
            if (Arrays.stream(establishment.getActivateOnRole()).anyMatch(ao->ao ==roll.getValue())) {
                if (establishment.getProductionOnType().equals(ProductionOnType.YourTurn) ||
                        establishment.getProductionOnType().equals(ProductionOnType.AnyonesTurn)) {
                    revenue += establishment.getProduction(establishments);
                }
            }
        }
        return revenue;
    }

    private int roll() {
        return random.nextInt(6);
    }

    public boolean HasWon() {
        return Landmarks().stream().anyMatch(lm -> !lm.isActive());
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
