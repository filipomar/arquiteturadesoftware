package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Game;
import models.User;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JsonUtils;
import utils.input.GameResultInput;
import views.html.index;
import controllers.vo.UserVO;

public class Application extends Controller {

	public static Result index() {
		final List<UserVO> vos = new ArrayList<UserVO>();
		for (final User user : User.findAll()) {
			vos.add(new UserVO(user));
		}

		return ok(index.render("Jogos :)", vos));
	}

	public static Result addGame() {
		Logger.info("BATEU!");

		final GameResultInput input = JsonUtils.parse(GameResultInput.class);

		if (input == null || !input.isvalid()) {
			return badRequest();
		}

		final User winner = ensureUser(input.getWinner());
		final User loser = ensureUser(input.getLoser());

		final Game game = new Game(winner, loser, input.getMoves());
		game.save();

		return ok();
	}

	private static User ensureUser(final String account) {
		User user = User.find(account);
		if (user == null) {
			user = new User(account);
			user.save();
		}
		return user;
	}

}
