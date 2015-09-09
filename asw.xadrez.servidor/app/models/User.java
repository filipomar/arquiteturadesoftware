package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5854422586239724109L;

	private static Finder<String, User> FINDER = new Finder<String, User>(String.class, User.class);

	@Id
	private String account;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = Game.LOSER_COLUMN_NAME)
	private final Set<Game> lost = new HashSet<Game>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = Game.WINNER_COLUMN_NAME)
	private final Set<Game> won = new HashSet<Game>();

	public String getAccount() {
		return account;
	}

	public Set<Game> getLost() {
		return lost;
	}

	public Set<Game> getWon() {
		return won;
	}

	public void setAccount(final String account) {
		this.account = account;
	}

	public User(final String account) {
		this.account = account;
	}

	public static User find(final String account) {
		return FINDER.byId(account);
	}

	public static List<User> findAll() {
		return FINDER.all();
	}

	@Override
	public String toString() {
		return "User [account=" + account + "]";
	}

}
