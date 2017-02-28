package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;



public class GameScreen extends MyAdapter implements Screen{

    private HiddenNature hn;
    private Texture diorama;
    private Stage gameStage;
    private SpriteBatch batch;
    private float currentZoom;
    private InputMultiplexer imp;
    private GestureDetector gd;


    public GameScreen(HiddenNature hiddenNature, Level level) {
        diorama = level.getLevelDiorama();
        imp = new InputMultiplexer();
        gd = new GestureDetector(this);
        hn = hiddenNature;

        batch = hn.getBatch();

        gameStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        imp.addProcessor(gameStage);
        imp.addProcessor(gd);
        imp.addProcessor(this);


        Gdx.input.setInputProcessor(imp);
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.log("gamescreen", "zoom");
        Gdx.app.log("INFO", "Zoom performed");

        ((OrthographicCamera)gameStage.getCamera()).zoom = (initialDistance / distance) * currentZoom;

        return true;
    }


    @Override
    public boolean touchDown (float x, float y, int pointer, int button) {
        currentZoom = ((OrthographicCamera)gameStage.getCamera()).zoom;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("screenY", " "+ screenY);
        Gdx.app.log("screenX", " "+ screenX);
        Gdx.app.log("pointer", " "+ pointer);

        return true;
    }

    @Override
    public boolean scrolled(int amount) {

        Gdx.app.log("gamescreen", "scrolled");
        Gdx.app.log("scroll", " "+amount);

        if(amount == -1){
            ((OrthographicCamera)gameStage.getCamera()).zoom -= 0.02;
        }
        if(amount == 1){
            ((OrthographicCamera)gameStage.getCamera()).zoom += 0.02;
        }
        // TODO Auto-generated method stub
        return true;
    }


    private void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gameStage.getCamera().translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gameStage.getCamera().translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            gameStage.getCamera().translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            gameStage.getCamera().translate(0, 3, 0);
        }
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

        handleInput();


        gameStage.act(Gdx.graphics.getDeltaTime());

        gameStage.getCamera().update();

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

