package hawkge.main.lobby.list;

import hawkge.storage.User;

/**
 * A Component for the lobbyList
 * @create on Apr 28, 2012
 * @author jorisvi
 */
public class UserListComponent implements Comparable<UserListComponent> {

    private UserState state;
    private User user;

    /**
     * 
     * @param online a Boolean object that says the state of the user
     * @param user an User object
     */
    public UserListComponent(UserState state, User user) {
        this.state = state;
        this.user = user;
    }
    
    public UserListComponent(User user) {
        this.user = user;
        state = null;
    }

    /**
     * Return a discription of this object in a String
     * @return a String object of this object
     */
    @Override
    public String toString() {
        return user.toString() +" - "+ state.name() +" - "+ user.getIP().toString();
    }
    
    /**
     * Set the online component of this user
     * @param online a Boolean object
     */
    public void setState(UserState state) {
        this.state = state;
    }
    
    /**
     * Returns the Boolean if the user is online
     * @return a Boolean object, true online, false ofline
     */
    public UserState getUserState() {
        return state;
    }
    
    /**
     * return a Object of the user
     * @return an User object
     */
    public User getUser() {
        return user;
    }

    /**
     * Compare to sort the object on online and name
     * @param o
     * @return 
     */
    public int compareTo(UserListComponent o) {
        int i;
        if (o.getUserState() == state) {
            i = 0;
        } else if (state == UserState.ONLINE) {
            i = -1;
        } else if(state == UserState.OFFLINE && o.getUserState() == UserState.BLOCKED) {
            i = -1;
        } else {
            i = 1;
        }
        if (i == 0) {
            i = user.getName().toLowerCase().compareTo(o.getUser().getName().toLowerCase());
        }
        return i;
    }

    /**
     * Equals if the user is the same.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserListComponent other = (UserListComponent) obj;
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }   
}