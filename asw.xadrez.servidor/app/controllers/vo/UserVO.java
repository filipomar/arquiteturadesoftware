package controllers.vo;

import models.Game;
import models.User;

public class UserVO {

	private final String account;

	private int points;

	public String getAccount() {
		return account;
	}

	public int getPoints() {
		return points;
	}

	public UserVO(final User user) {
		this.account = user.getAccount();
		this.points = 0;

		for (final Game game : user.getWon()) {
			this.points += game.getMoves() * Game.WIN_VALUE;
		}

		for (final Game game : user.getLost()) {
			this.points += game.getMoves() * Game.LOST_VALUE;
		}
	}

}
