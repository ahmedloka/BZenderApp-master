package apps.sharabash.bzender.Models.getCiry;

import java.util.List;

class SearchResponseModel {
    private List<Results> results;

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }
}
