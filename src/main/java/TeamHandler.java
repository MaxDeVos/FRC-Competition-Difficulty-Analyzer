import com.cpjd.main.TBA;
import com.cpjd.models.events.Award;
import com.cpjd.models.events.AwardRecipient;
import com.cpjd.models.teams.Team;
import com.cpjd.utils.exceptions.DataNotFoundException;

import java.util.ArrayList;

class TeamHandler {

    public enum AwardType{
        WINNER, CHAIRMANS, EI
    }

    private TBA tba;
    private ELOManager eloManager;
    TeamHandler(TBA tba_){
        eloManager = new ELOManager();
        tba = tba_;
    }

    int getAwardWinner(AwardType type, int year, String event){
        String awardQuery = "";
        switch(type){
            case WINNER:
                awardQuery = "Regional Winner";
                break;
            case CHAIRMANS:
                awardQuery = "hairman";
                break;
            case EI:
                awardQuery = "Engineering Inspiration";
                break;
        }

        try{
            for(Award a: tba.getEventAwards(year+event)){
                if(a.getName().contains(awardQuery)){
                    for(AwardRecipient ar: a.getRecipients()){
                        String str = ar.getTeamKey();
                        str = str.replaceAll("[^\\d.]", "");
                        return Integer.parseInt(str);
                    }
                }
            }
        }
        catch(DataNotFoundException exception){
            return 0;
        }

        return 0;
    }

    ArrayList<Integer> getEventWinners(int year, String event){
        ArrayList<Integer> out = new ArrayList<>();
        try{
            for(Award a: tba.getEventAwards(year+event)){
                if(a.getName().contains("Regional Winner")){
                    for(AwardRecipient ar: a.getRecipients()){
                        String str = ar.getTeamKey();
                        str = str.replaceAll("[^\\d.]", "");
                        out.add(Integer.parseInt(str));
                    }
                }
            }
        }
        catch(DataNotFoundException exception){
        }
        for(int i = 0; i < 5; i++){
            out.add(0);
        }
        return out;
    }

    double getEventAverageELO(int year, String event){
        try{
            Team[] teams = tba.getEventTeams(year+event);
            int runningSum = 0;
            for(Team t:teams){
                runningSum += eloManager.getELO((int) t.getTeamNumber(), year);
            }
            return runningSum / (double) teams.length +1;
        }
        catch(DataNotFoundException e){
        }
        return 0;

    }


}
