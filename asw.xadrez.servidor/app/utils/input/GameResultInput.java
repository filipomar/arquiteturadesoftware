package utils.input;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResultInput {
	@JsonProperty
	private String loser;

	@JsonProperty
	private int moves;

	@JsonProperty
	private String winner;

	public String getLoser() {
		return loser;
	}

	public int getMoves() {
		return moves;
	}

	public String getWinner() {
		return winner;
	}

	public boolean isvalid() {
		if (this.moves <= 0 || StringUtils.isBlank(this.winner) || StringUtils.isBlank(this.loser)) {
			return false;
		}

		return true;
	}
}
