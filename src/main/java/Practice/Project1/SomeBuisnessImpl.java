package Practice.Project1;

import org.springframework.beans.factory.annotation.Autowired;

public class SomeBuisnessImpl {

    private DataService dataService;

    SomeBuisnessImpl(DataService dataService){
        this.dataService=dataService;
    }
    public int findMax(){
        int[] datas = dataService.retrieveAllData();
        int maxi=Integer.MIN_VALUE;

        for(int value:datas){
            if(value>maxi) maxi=value;
        }
        return maxi;
    }

}

interface DataService{
    int[] retrieveAllData();
}
