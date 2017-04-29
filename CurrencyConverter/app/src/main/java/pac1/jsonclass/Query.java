package pac1.jsonclass;

/**
 * Created by Phupc on 4/7/2017.
 */

public class Query {
    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    private Results results;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private String count;
}
