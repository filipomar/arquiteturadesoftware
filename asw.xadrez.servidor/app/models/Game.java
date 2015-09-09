package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Game extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4710363804542236748L;

	public static final String LOSER_COLUMN_NAME = "loser";
	public static final String WINNER_COLUMN_NAME = "winner";

	public static final long WIN_VALUE = 3;
	public static final long LOST_VALUE = -1;

	@Id
	private Long id;

	@JoinColumn(name = LOSER_COLUMN_NAME, nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private User loser;

	@Column(nullable = false)
	private long moves;

	@Column(nullable = false)
	private long points;

	@JoinColumn(name = WINNER_COLUMN_NAME, nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private User winner;

	public User getLoser() {
		return loser;
	}

	public long getMoves() {
		return moves;
	}

	public User getWinner() {
		return winner;
	}

	public void setLoser(final User loser) {
		this.loser = loser;
	}

	public void setMoves(final long moves) {
		this.moves = moves;
	}

	public void setWinner(final User winner) {
		this.winner = winner;
	}

	public Game() {
		super();
	}

	public Game(final User winner, final User loser, final long moves) {
		this();
		this.winner = winner;
		this.loser = loser;
		this.moves = moves;
	}

}
