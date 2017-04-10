package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen extends MyAdapter implements Screen{

    private HiddenNature hn;
    private Texture diorama;
    private Stage gameStage;
    private SpriteBatch batch, batch2;
    private float currentZoom;
    private Entity menuOpenButton;
    private InputMultiplexer imp;
    private GestureDetector gd;
    private float maxZoom, minZoom;
    private boolean menuOpen;
    private float menuButtonOriginalWidth, menuButtonOriginalHeight;
    private float moveX, moveY;
    private Level level;
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private PrefHandler prefs;
    private Preferences globalPrefs;

    @Override
    public void dispose() {
        batch.dispose();
        batch2.dispose();
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
        globalPrefs.flush();

        menuOpenButton = new Entity("PauseMenu.png", "PauseMenuPushedButton.png", 690f, 390f, 1, true, 0.25f);

        batch = hn.getBatch();

        gameStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        update(isPauseMenu);
    }

    public void update(boolean isPauseMenu) {
        gameStage.dispose();
        imp.clear();
        gameStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        for (Entity e : entities) {
            if (!e.isFound()) {
                gameStage.addActor(e);
            }
        }

        gameStage.addActor(menuOpenButton);

        imp.addProcessor(gameStage);
        imp.addProcessor(gd);
        imp.addProcessor(this);

        Gdx.input.setInputProcessor(imp);
        maxZoom = ((OrthographicCamera)gameStage.getCamera()).zoom;
        minZoom = 0.60f;

        if (isPauseMenu){

            gameStage.getCamera().position.set(level.getCamPos());
            ((OrthographicCamera) gameStage.getCamera()).zoom = level.getZoom();
        }
    }

    public void setMenuOpen(boolean mO){
        menuOpen = mO;
    }

    public boolean isMenuOpen(){
        return menuOpen;
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
                hn.pauseMenu.update();
                hn.setScreen(hn.pauseMenu);
                entity.resetAction();
                break;
        }

        if (entity.getAction() < 0){
            entity.setFound(true);
            entity.resetAction();

//            Object B = new Object((String)A.getProperty1().toString(), A.getProperty2()...);
        }

//        for(Person p : oldList) {
//            newList.add(p.clone());
//        }

    }


    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.log("gamescreen", "zoom");
        Gdx.app.log("INFO", "Zoom performed");

        if (minZoom < (initialDistance / distance) * currentZoom &&
                maxZoom > (initialDistance / distance) * currentZoom) {

            ((OrthographicCamera) gameStage.getCamera()).zoom = (initialDistance / distance) * currentZoom;
        }

        solidElements();

        return true;
    }

    @Override
    public boolean touchDown (float x, float y, int pointer, int button) {
        currentZoom = ((OrthographicCamera)gameStage.getCamera()).zoom;
        Gdx.app.log("coordinates", " ");
        int realX = Gdx.input.getX();
        int realY = Gdx.input.getY();
        Vector3 touchPos = new Vector3(realX, realY, 0);
        Gdx.app.log("Game", "real X = " + realX);
        Gdx.app.log("Game", "real Y = " + realY);
        Gdx.app.log("Game", "world X = " + touchPos.x);
        Gdx.app.log("Game", "world Y = " + touchPos.y);
        moveX = x;
        moveY = y;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("touchDragged", "screenY = "+ screenY);
        Gdx.app.log("touchDragged", "screenX = "+ screenX);
        Gdx.app.log("touchDragged", "pointer = "+ pointer);

        float lerp = 0.3f;

        gameStage.getCamera().position.add(0 - Gdx.input.getDeltaX() * lerp, Gdx.input.getDeltaY() * lerp, 0);
        return true;
    }


    @Override
    public boolean scrolled(int amount) {
        Gdx.app.log("INFO", "Zoom"+ ((OrthographicCamera) gameStage.getCamera()).zoom);
        Gdx.app.log("INFO", "pos"+ ((OrthographicCamera) gameStage.getCamera()).position);

        Gdx.app.log("gamescreen", "scrolled");
        Gdx.app.log("scroll", " "+amount);

        if(amount == -1 && minZoom < ((OrthographicCamera) gameStage.getCamera()).zoom){
            ((OrthographicCamera)gameStage.getCamera()).zoom -= 0.02;
        }
        if(amount == 1 && maxZoom > ((OrthographicCamera) gameStage.getCamera()).zoom){
            ((OrthographicCamera)gameStage.getCamera()).zoom += 0.02;
        }

        solidElements();
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

        if(Gdx.input.isTouched()){
            float realX = gameStage.getViewport().getLeftGutterWidth();
            float realY = gameStage.getViewport().getBottomGutterHeight();

            Vector3 touchPos = new Vector3(realX, realY, 0);

            gameStage.getCamera().project(touchPos);

        }
    }

    private void moveCamera() {
        float halfOfCurrentViewportWidth = ((gameStage.getCamera().viewportWidth * ((OrthographicCamera) gameStage.getCamera()).zoom) / 2f);
        float halfOfCurrentViewportHeight = ((gameStage.getCamera().viewportHeight * ((OrthographicCamera) gameStage.getCamera()).zoom) / 2f);

        //Check the vertical camera.
        if(gameStage.getCamera().position.x - halfOfCurrentViewportWidth < 0f) {  //Is going off the left side.
            //Snap back.
            float amountGoneOver = gameStage.getCamera().position.x - halfOfCurrentViewportWidth;
            gameStage.getCamera().position.x += Math.abs(amountGoneOver);
        } else if(gameStage.getCamera().position.x + halfOfCurrentViewportWidth > gameStage.getCamera().viewportWidth) {
            //Snap back.
            float amountGoneOver = (gameStage.getCamera().viewportWidth - (gameStage.getCamera().position.x + halfOfCurrentViewportWidth));
            gameStage.getCamera().position.x -= Math.abs(amountGoneOver);
        }

        //Check the horizontal camera.
        if(gameStage.getCamera().position.y + halfOfCurrentViewportHeight > gameStage.getCamera().viewportHeight) {
            float amountGoneOver = (gameStage.getCamera().position.y + halfOfCurrentViewportHeight) - gameStage.getCamera().viewportHeight;
            gameStage.getCamera().position.y -= Math.abs(amountGoneOver);
        } else if(gameStage.getCamera().position.y - halfOfCurrentViewportHeight < 0f) {
            float amountGoneOver = (gameStage.getCamera().position.y - halfOfCurrentViewportHeight);
            gameStage.getCamera().position.y += Math.abs(amountGoneOver);
        }
    }

    public void solidElements() {
        float currentViewportWidth = ((gameStage.getCamera().viewportWidth * ((OrthographicCamera) gameStage.getCamera()).zoom));
        float currentViewportHeight = ((gameStage.getCamera().viewportHeight * ((OrthographicCamera) gameStage.getCamera()).zoom));

        menuOpenButton.setX((currentViewportWidth - menuOpenButton.getWidth()) + (gameStage.getCamera().position.x - (currentViewportWidth / 2)));
        menuOpenButton.setY((currentViewportHeight - menuOpenButton.getHeight()) + (gameStage.getCamera().position.y - (currentViewportHeight / 2)));

        menuOpenButton.setSize((menuOpenButton.getTexture().getWidth() * 0.25f) * ((OrthographicCamera) gameStage.getCamera()).zoom,
                (menuOpenButton.getTexture().getHeight() * 0.25f) * ((OrthographicCamera) gameStage.getCamera()).zoom);
    }

    @Override
    public void render(float delta) {

        if (menuOpen == false) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            gameStage.getBatch().begin();
            gameStage.getBatch().draw(diorama, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
            gameStage.getBatch().end();

            handleInput();
            moveCamera();
            solidElements();

            gameStage.act(Gdx.graphics.getDeltaTime());
            gameStage.draw();
            gameStage.getCamera().update();


            getEntityID(menuOpenButton);



            for (Entity e : entities) {

                getEntityID(e);

                if (e.isFound()) {
                    for (Entity f : originals) {
                        if (e.getButtonID() == f.getButtonID()) {
                            f.setFound(true);
                        }
                    }


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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
