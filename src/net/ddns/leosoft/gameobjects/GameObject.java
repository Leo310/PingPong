package net.ddns.leosoft.gameobjects;

import net.ddns.leosoft.main.Game;
import net.ddns.leosoft.view.RenderHandler;

public interface GameObject {
	void render(RenderHandler renderHandler, int xZoom, int yZoom);
	void update(Game game);
}
