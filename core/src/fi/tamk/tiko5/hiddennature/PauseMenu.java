package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by ghost on 9.3.2017.
 */

public class PauseMenu implements Screen {

    private HiddenNature hn;
    private Stage pauseStage;
    private SpriteBatch batch;
    private Level level;
    private GameScreen gameScreen;
    private Texture background;
    private Entity menuButton;



    public PauseMenu(HiddenNature hiddenNature, Level l, GameScreen gS){

        hn = hiddenNature;
        level = l;
        gameScreen = gS;
        batch = hn.getBatch();

        background = new Texture(Gdx.files.internal("laatikko.png"));
        menuButton = new Entity("return.png", "returnpressed.png", 390f, 290f, 1, true);

        pauseStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        pauseStage.addActor(menuButton);

        Gdx.input.setInputProcessor(pauseStage);
    }


    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("pauseMenu", "no actions");
                break;

            case 1:Gdx.app.log("pauseMenu", "menubutton");
                //hn.setScreen(gameScreen);
                entity.resetAction();
                break;
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //if (gameScreen.isMenuOpen() == true){
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            pauseStage.getBatch().begin();
            pauseStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());
            pauseStage.getBatch().end();

            pauseStage.act(Gdx.graphics.getDeltaTime());
            pauseStage.draw();

            getEntityID(menuButton);
        //}
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
