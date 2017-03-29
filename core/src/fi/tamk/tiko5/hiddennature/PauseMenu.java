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
    private GameScreen gameScreen;
    private Texture background;
    private Entity menuButton, returnButton, textureChange;
    private Array<Entity>entities = new Array<Entity>();
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();


    public PauseMenu(HiddenNature hiddenNature, Level l){

        hn = hiddenNature;
        level = l;
        batch = hn.getBatch();
        entities = level.getEntities();
        originals = level.getOriginals();
        silhouettes = level.getSilhouettes();

        background = new Texture(Gdx.files.internal("pauseScreen.png"));
        menuButton = new Entity("PauseMenu.png", "PauseMenu.png", 690f, 390f, 1, true, 0.25f);
        returnButton = new Entity("X.png", "X.png", 690f, 290f, 2, true, 0.25f);



//        for(int i = 0; i < entities.size; i++){
//            Gdx.app.log("object:" + i, ""+entities.get(i).isFound());
//            if (entities.get(i).isFound()){
//                for(int j = 0; j < originals.size; j++){
//                    Gdx.app.log("pauseobject:" + j, ""+entities.get(j).isFound());
//                    Entity tmp = originals.get(j);
//                    if(entities.get(i).getAction() == originals.get(j).getAction()){
//                        tmp.setFound(true);
//                        tmp.pressedTexture();
//                        originals.set(j, null);
//                        originals.set(j, tmp);
//                    }
//                }
//            }
//        }
//        for (Entity e : originals) {
//            if (e.isFound()) {
//                e.pressedTexture();
//            }
//        }

//        for (Entity e : entities) {
//
//            if (e.isFound()) {
//                int counter= 0;
//                for (Entity n : originals) {
//
//                    if (e.getAction() == n.getAction()) {
//                        Entity t = n;
//                        t.pressedTexture();
//                        originals.set(counter, t);
//                    }
//                }
//            }
//        }

        pauseStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        pauseStage.addActor(menuButton);
        pauseStage.addActor(returnButton);

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


        Gdx.input.setInputProcessor(pauseStage);
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
                hn.setScreen(new GameScreen(hn, level, true));
                entity.resetAction();
                break;

            case 2:Gdx.app.log("pauseMenu", "back");
                hn.setScreen(new LevelSelect(hn));

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

    @Override
    public void dispose() {

    }
}
