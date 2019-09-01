import com.cpjd.main.TBA;
import com.cpjd.models.events.Award;
import com.cpjd.models.events.AwardRecipient;

public class TeamHandler {

    public enum AwardType{
        WINNER, CHAIRMANS, EI
    }

    private TBA tba;
    public TeamHandler(TBA tba_){
        tba = tba_;
    }

    public int getAwardWinner(AwardType type, int year, String event){
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
        catch(Exception exception){
            return 0;
        }

        return 0;
    }

}
