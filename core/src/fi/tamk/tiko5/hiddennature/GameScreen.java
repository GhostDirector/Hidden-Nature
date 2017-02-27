package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends GestureDetector.GestureAdapter implements Screen {

    private HiddenNature hn;
    private Texture diorama;
    private Stage gameStage;
    private SpriteBatch batch;


    public GameScreen(HiddenNature hiddenNature, Level level){
        diorama = level.getLevelDiorama();

        hn = hiddenNature;

        batch = hn.getBatch();

        gameStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);


        Gdx.input.setInputProcessor(gameStage);
        Gdx.input.setInputProcessor(new GestureDetector(this));

    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance){
        return true;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(diorama, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
        gameStage.getBatch().end();

        gameStage.getBatch().begin();
        gameStage.getBatch().draw(diorama, 100, 100, hn.getWORLD_WIDTH() - 200, hn.getWORLD_HEIGHT() - 200);
        gameStage.getBatch().end();




        gameStage.act(Gdx.graphics.getDeltaTime());

        gameStage.draw();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

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
}
