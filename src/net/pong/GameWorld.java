package net.pong;

import java.awt.event.KeyEvent;

import com.bStone.Start;
import com.bStone.engine.render.drawable.render.Render;
import com.bStone.engine.render.drawable.view.ViewPort;
import com.bStone.engine.resource.render.RenderRegistry;
import com.bStone.engine.resource.timer.ITimeable;
import com.bStone.engine.resource.timer.Timer;
import com.bStone.engine.resource.timer.TimerRegistry;
import com.qSilver.opengl.ViewPortGL;
import com.qSilver.opengl.WorldGL;
import com.qSilver.render.RenderLivingEntity;
import com.qSilver.render.gui.GuiScreen;
import com.qSilver.render.string.StringRenderer;

public class GameWorld extends WorldGL implements ITimeable
{
	public GameWorld()
	{
		super("GameWorld");
	}

	@Override
	protected GuiScreen[] setupGuiScreen()
	{
		return new GuiScreen[] {new GameScreen(this)};
	}

	@Override
	protected ViewPort setupViewPort()
	{
		return new ViewPortGL(Start.getApplication().getRenderEngine().getWindow().getCanvas(), 0, 0, 0F, 640, 480).setClients(this);
	}

	@Override
	public void onLoad()
	{
		super.onLoad();
		RenderRegistry.INSTANCE.register(Paddle.class, new RenderLivingEntity());
		RenderRegistry.INSTANCE.register(PaddleAuto.class, new RenderLivingEntity());
		RenderRegistry.INSTANCE.register(Ping.class, new RenderLivingEntity());
	}

	@Override
	public void onUnload()
	{
		super.onUnload();
	}

	protected int scoreA;
	protected int scoreB;
	protected Timer timer = TimerRegistry.INSTANCE.register("ScoreKeeper", new Timer(60, 1000).setClients(this));

	public void resetScore()
	{
		this.scoreA = this.scoreB = 0;
		this.timer.reset();
	}

	protected Paddle paddle;
	protected PaddleAuto paddleAuto;
	protected Ping ping;

	@Override
	public void onInitiate()
	{
		super.onInitiate();

		this.instantiate(this.paddle = new Paddle(-200, 0));
		this.instantiate(this.paddleAuto = new PaddleAuto(200, 0));
		this.instantiate(this.ping = new Ping(0, 0, 30));

		this.timer.start();
	}

	@Override
	public void onTerminate()
	{
		super.onTerminate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public void onContext()
	{
		super.onContext();
	}

	@Override
	public void onRender()
	{
		Render.gl().glColor3f(1F, 1F, 1F);
		StringRenderer.renderString("P O N G", 0, 0, 2, StringRenderer.CENTERED);
		StringRenderer.renderString("" + this.timer.getCount(), 0, 64, 2, StringRenderer.CENTERED);
		StringRenderer.renderString("" + this.scoreA, -300, -200, 4, StringRenderer.LEFT_ALIGNED);
		StringRenderer.renderString("" + this.scoreB, 300, -200, 4, StringRenderer.RIGHT_ALIGNED);
		super.onRender();
	}

	@Override
	public void onInput()
	{
		super.onInput();
		if (Start.getApplication().getInputEngine().isKeyPressed(KeyEvent.VK_UP))
		{
			this.paddle.setPosY(this.paddle.getPosY() - 2);
		}

		if (Start.getApplication().getInputEngine().isKeyPressed(KeyEvent.VK_DOWN))
		{
			this.paddle.setPosY(this.paddle.getPosY() + 2);
		}
	}

	@Override
	public void onViewScale()
	{
		super.onViewScale();
	}

	@Override
	public void onCount(Timer parTimer) {}

	@Override
	public void alarm(Timer parTimer)
	{
		this.resetScore();
	}
}
