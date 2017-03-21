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
    private Array<Entity> objects, pauseObjects, foundEntities;

    public PauseMenu(HiddenNature hiddenNature, Level l){
        hn = hiddenNature;
        level = l;
        batch = hn.getBatch();
        objects = new Array<Entity>(this.level.getObjects());
        pauseObjects = new Array<Entity>(this.level.getPauseObjects());
        foundEntities = new Array<Entity>(this.level.getFoundEntities());

        background = new Texture(Gdx.files.internal("laatikko.png"));
        menuButton = new Entity("return.png", "returnpressed.png", 690f, 390f, 1, true);
        returnButton = new Entity("previous.png", "previouspressed.png", 690f, 290f, 2, true);



//        for(int i = 0; i < objects.size; i++){
//            Gdx.app.log("object:" + i, ""+objects.get(i).isFound());
//            if (objects.get(i).isFound()){
//                for(int j = 0; j < pauseObjects.size; j++){
//                    Gdx.app.log("pauseobject:" + j, ""+objects.get(j).isFound());
//                    Entity tmp = pauseObjects.get(j);
//                    if(objects.get(i).getAction() == pauseObjects.get(j).getAction()){
//                        tmp.setFound(true);
//                        tmp.pressedTexture();
//                        pauseObjects.set(j, null);
//                        pauseObjects.set(j, tmp);
//                    }
//                }
//            }
//        }
//        for (Entity e : pauseObjects) {
//            if (e.isFound()) {
//                e.pressedTexture();
//            }
//        }

//        for (Entity e : objects) {
//
//            if (e.isFound()) {
//                int counter= 0;
//                for (Entity n : pauseObjects) {
//
//                    if (e.getAction() == n.getAction()) {
//                        Entity t = n;
//                        t.pressedTexture();
//                        pauseObjects.set(counter, t);
//                    }
//                }
//            }
//        }

        pauseStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);
        pauseStage.addActor(menuButton);
        pauseStage.addActor(returnButton);

        for (Entity e : pauseObjects) {
            pauseStage.addActor(e);
        }

        for (int i = 0; i < objects.size; i++){
            if (objects.get(i).isFound()) {
                foundEntities.get(i).setPosition(pauseObjects.get(i).getX(), pauseObjects.get(i).getY());
                pauseStage.addActor(foundEntities.get(i));
            }
        }

        Gdx.input.setInputProcessor(pauseStage);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("pauseMenu", "no actions");
                break;

            case 1:Gdx.app.log("pauseMenu", "menubutton");
                level.setObjects(objects);
                level.setPauseObjects(pauseObjects);
                level.setFoundEntities(foundEntities);
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
