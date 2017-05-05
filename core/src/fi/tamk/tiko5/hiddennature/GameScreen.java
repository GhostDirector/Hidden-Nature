package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen extends MyAdapter implements Screen{

    private HiddenNature hn;
    private Texture diorama;
    private Stage gameStage;
    private SpriteBatch batch;
    private Entity menuOpenButton, tutorialBox, completeBox;
    private InputMultiplexer imp;
    private GestureDetector gd;
    private boolean menuOpen;
    private Level level;
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private PrefHandler prefs;
    private Preferences globalPrefs;
    private boolean showTutorial;
    private int counter;

    @Override
    public void dispose() {
        batch.dispose();
        gameStage.dispose();
        diorama.dispose();
        hn.dispose();
    }

    public GameScreen(HiddenNature hiddenNature, Level l, boolean isPauseMenu) {
        level = l;
        diorama = level.getLevelDiorama();
        imp = new InputMultiplexer();
        hn = hiddenNature;
        menuOpen = false;
        entities = level.getEntities();
        originals = level.getOriginals();
        silhouettes = level.getSilhouettes();
        prefs = new PrefHandler(level);
        gd = new GestureDetector(this);

        globalPrefs = Gdx.app.getPreferences("settings");

        globalPrefs.putBoolean("Reset"+level.getLevelID(), false);
        showTutorial = globalPrefs.getBoolean("Tutorial", true);
        globalPrefs.putBoolean("Tutorial", false);
        globalPrefs.flush();

        menuOpenButton = new Entity("PauseMenu.png", "PauseMenuPushedButton.png", 690f, 390f, 1, true, 0.25f);

        batch = hn.getBatch();
        
        selectScreen(isPauseMenu);
    }

    public void selectScreen(boolean isPauseMenu) {
        if (gameStage != null) {
            gameStage.dispose();
        }
        
        imp.clear();
        gameStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        for (Entity e : entities) {
            if (!e.isFound()) {
                gameStage.addActor(e);
            }
        }

        gameStage.addActor(menuOpenButton);
        tutorialBox = new Entity(hn.getLocalization().get("tutBox"), hn.getLocalization().get("tutBox"), 120f, 0f, 2, true, 0.48f);
        completeBox = new Entity(hn.getLocalization().get("completeBox"), hn.getLocalization().get("completeBox"), 0f, 0f, 1, true, 1f);
        completeBox.setSize(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());

        if (showTutorial) {
            gameStage.addActor(tutorialBox);
        }

        imp.addProcessor(gameStage);
        imp.addProcessor(gd);
        imp.addProcessor(this);

        Gdx.input.setInputProcessor(imp);

        if (isPauseMenu){

            gameStage.getCamera().position.set(level.getCamPos());
            ((OrthographicCamera) gameStage.getCamera()).zoom = level.getZoom();
        }
        hn.setScreen(this);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("GameScreen", "no actions");
                entity.resetAction();
                break;

            case 1: Gdx.app.log("GameScreen", "pausemenu");
                level.setOriginals(originals);
                level.setEntities(entities);
                level.setSilhouettes(silhouettes);
                level.setZoom(((OrthographicCamera) gameStage.getCamera()).zoom);
                level.setCamPos(gameStage.getCamera().position);
                prefs.save();
                hn.pauseMenu.selectScreen();
                entity.resetAction();
                break;

            case 2: showTutorial = false;
                entity.resetAction();
                selectScreen(false);
                break;
        }

        if (entity.getAction() < 0){
            entity.setFound(true);
            entity.resetAction();

        }
    }

    @Override
    public void render(float delta) {

        if (!menuOpen) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            gameStage.getBatch().begin();
            gameStage.getBatch().draw(diorama, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
            gameStage.getBatch().end();

            gameStage.act(Gdx.graphics.getDeltaTime());
            gameStage.draw();
            gameStage.getCamera().update();

            
            getEntityID(tutorialBox);
            
            getEntityID(menuOpenButton);
            getEntityID(completeBox);

            counter = 0;
            for (Entity e : entities) {

                getEntityID(e);

                if (e.isFound()) {
                    for (Entity f : originals) {
                        if (e.getButtonID() == f.getButtonID()) {
                            f.setFound(true);
                        }
                    }
                    for (Entity f : silhouettes) {
                        if (e.getButtonID() == f.getButtonID()) {
                            f.setFound(true);
                        }
                    }

                    counter++;
                    System.out.println(counter);
                }
                if (counter == entities.size) {
                    gameStage.addActor(completeBox);
                }
            }

        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        hn.gameMusic.pause();
    }

    @Override
    public void resume() {
        if (hn.isSound()){
            hn.gameMusic.play();
        }
    }

    @Override
    public void hide() {

    }
}
