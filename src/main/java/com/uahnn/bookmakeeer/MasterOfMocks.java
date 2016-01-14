package com.uahnn.bookmakeeer;

import com.uahnn.bookmakeeer.model.Game;
import com.uahnn.bookmakeeer.model.Team;
import java.util.*;

/**
 * Created by Robin on 13.01.16
 */
public class MasterOfMocks {

    List<Team> teams;
    int lastMockedTeamId = -1;

    private MasterOfMocks(){
        this.teams = new ArrayList<Team>();
        teams.add(new Team("ALB"));
        teams.add(new Team("AUT"));
        teams.add(new Team("BEL"));
        teams.add(new Team("CRO"));
        teams.add(new Team("CZE"));
        teams.add(new Team("ENG"));
        teams.add(new Team("ESP"));
        teams.add(new Team("FRA"));
        teams.add(new Team("GER"));
        teams.add(new Team("HUN"));
        teams.add(new Team("IRL"));
        teams.add(new Team("ISL"));
        teams.add(new Team("ITA"));
        teams.add(new Team("NIR"));
        teams.add(new Team("POL"));
        teams.add(new Team("POR"));
        teams.add(new Team("ROU"));
        teams.add(new Team("RUS"));
        teams.add(new Team("SUI"));
        teams.add(new Team("SVK"));
        teams.add(new Team("SWE"));
        teams.add(new Team("TUR"));
        teams.add(new Team("UKR"));
        teams.add(new Team("WAL"));
    }

    private static MasterOfMocks instance = null;


    public static MasterOfMocks getInstance(){
        if(instance == null){
            instance = new MasterOfMocks();
        }
        return instance;
    }


    public Team mockTeam(){
        Random generator = new Random();
        int i;
        do {
             i = generator.nextInt(24);
        }while(i == lastMockedTeamId);

        return teams.get(i);
    }

    public Game mockGame(){
        Game game = new Game();
        game.setAwayTeam(mockTeam());
        game.setHomeTeam(mockTeam());
        game.setStartTime(new Date());
        return game;
    }

    public List<Game> mockGames(int amount){
        if(amount < 1) amount = 1;
        ArrayList<Game> games = new ArrayList<Game>();
        for(int i = 0; i < amount; i++){
            games.add(mockGame());
        }
        return games;
    }
}
