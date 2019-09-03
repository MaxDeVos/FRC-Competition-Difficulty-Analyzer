import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ELOManager {

    ELOManager(){

    }

    int getELO(int team, int year){
        String csvFile = "elos.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line = "";
            while ((line = br.readLine()) != null) {

                String cvsSplitBy = ",";
                String[] value = line.split(cvsSplitBy);

                if(Integer.parseInt(value[0]) == team){
                    switch(year){
                        case 2015:
                            return Integer.parseInt(value[1]);

                        case 2016:

                            return Integer.parseInt(value[2]);

                        case 2017:

                            return Integer.parseInt(value[3]);

                        case 2018:

                            return Integer.parseInt(value[4]);

                        case 2019:

                            return Integer.parseInt(value[5]);
                    }
                }

            }

        } catch (IOException e) {
//            e.printStackTrace();
        }



        return 0;
    }




}
