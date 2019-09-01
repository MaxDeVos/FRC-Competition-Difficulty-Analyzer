import com.cpjd.main.TBA;
import com.cpjd.models.events.Award;
import com.cpjd.models.events.AwardRecipient;
import com.cpjd.models.events.Event;
import com.cpjd.models.teams.Team;

public class Main {


    public static void main(String args[]){
        String compKey = "mndu2";
        TBA tba = new TBA();
        TBA.setAuthToken("uJvxP1GDDT7O6Y0Ys7xrGhVUdntYErICAtz9uDc74LcpxPFz3vqcpMrHG94MuMPC ");
        TeamHandler handler = new TeamHandler(tba);

        for(int i = 2015; i < 2020; i++){
            System.out.print(handler.getAwardWinner(TeamHandler.AwardType.CHAIRMANS, i, compKey));
            System.out.print('\t');
        }
        System.out.println();
        for(int i = 2015; i < 2020; i++){
            System.out.print(handler.getAwardWinner(TeamHandler.AwardType.EI, i, compKey));
            System.out.print('\t');
        }


    }


}
