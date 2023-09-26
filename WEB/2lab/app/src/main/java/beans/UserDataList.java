package beans;

import java.io.Serializable;
import java.util.List;

public class UserDataList implements Serializable {
    
    private List<UserData> userDataList;

    public UserDataList (List<UserData> anUserDataList) {
        userDataList = anUserDataList;        
    }

    public UserDataList () {
        super();
    }

    public boolean isUserDataListIsNull() {
        return userDataList == null;
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userDataList == null) ? 0 : userDataList.hashCode());
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
        UserDataList other = (UserDataList) obj;
        if (userDataList == null) {
            if (other.userDataList != null)
                return false;
        } else if (!userDataList.equals(other.userDataList))
            return false;
        return true;
    }

}
