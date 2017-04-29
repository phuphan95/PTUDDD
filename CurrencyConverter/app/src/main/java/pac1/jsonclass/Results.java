package pac1.jsonclass;

import java.util.List;

/**
 * Created by Phupc on 4/7/2017.
 */

public class Results {
    public List<Rate> getRate() {
        return rate;
    }

    public void setRate(List<Rate> rate) {
        this.rate = rate;
    }

    private List<Rate> rate;
}
