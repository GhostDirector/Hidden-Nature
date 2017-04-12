package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by ghost on 9.3.2017.
 */

public class PauseMenu implements Screen {

    private HiddenNature hn;
    private Stage pauseStage;
    private SpriteBatch batch;
    private Level level;
    private Texture background;
    private Entity menuButton, returnButton, textureChange;
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();

    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
        pauseStage.dispose();
        hn.dispose();
    }

    public PauseMenu(HiddenNature hiddenNature, Level l){

        hn = hiddenNature;
        level = l;
        batch = hn.getBatch();
        entities = level.getEntities();
        originals = level.getOriginals();
        silhouettes = level.getSilhouettes();

        background = new Texture(Gdx.files.internal("PauseMenuFancy.jpg"));
        menuButton = new Entity("PauseMenu.png", "PauseMenuPushedButton.png", 690f, 390f, 1, true, 0.25f);
        returnButton = new Entity("X.png", "xPushedButton.png", 690f, 290f, 2, true, 0.25f);
        
        selectScreen();
    }

    public void selectScreen() {
        if (pauseStage != null) {
            pauseStage.dispose();
        }
        
        pauseStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        for (int i = 0; i < originals.size; i++){
            if (originals.get(i).isFound()) {
                originals.get(i).setPosition(silhouettes.get(i).getX(), silhouettes.get(i).getY());

            }

        }
        for (Entity e : silhouettes) {
            pauseStage.addActor(e);
        }

        for (Entity e : originals) {
            if (e.isFound()) {
                pauseStage.addActor(e);
            }
        }

        pauseStage.addActor(menuButton);
        pauseStage.addActor(returnButton);

        Gdx.input.setInputProcessor(pauseStage);
        hn.setScreen(this);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("pauseMenu", "no actions");
                entity.resetAction();
                break;

            case 1:Gdx.app.log("pauseMenu", "menubutton");
                level.setEntities(entities);
                level.setOriginals(originals);
                level.setSilhouettes(silhouettes);
                hn.gameScreen.selectScreen(true);
                entity.resetAction();
                break;

            case 2:Gdx.app.log("pauseMenu", "back");
                hn.levelSelect.selectScreen();
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
        getEntityID(returnButton);
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
}
