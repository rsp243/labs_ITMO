package lab.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;

@ManagedBean(value = "ResultsList")
@ApplicationScoped
public class TableResultsBean implements Serializable {
    private List<Result> resultsList;

    public TableResultsBean(LinkedList<Result> anResultList) {
        resultsList = anResultList;   
    }

    public TableResultsBean() {
        super();
        resultsList = new LinkedList<>();
    }

    public List<Result> getResultsList() {
        return resultsList;
    }

    public void setResultsList(LinkedList<Result> resultsList) {
        this.resultsList = resultsList;
    }

    @Override
    public String toString() {
        return "TableResultsBean [resultsList=" + resultsList + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resultsList == null) ? 0 : resultsList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TableResultsBean other = (TableResultsBean) obj;
        if (resultsList == null) {
            if (other.resultsList != null)
                return false;
        } else if (!resultsList.equals(other.resultsList))
            return false;
        return true;
    }
}
