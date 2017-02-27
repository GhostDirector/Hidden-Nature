package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu implements Screen {

    private HiddenNature hn;

    private Texture background;

    private Stage mainStage;

    private SpriteBatch batch;

    private Entity entity1;
    private Entity entity2;
    private Entity entity3;
    private Entity entity4;

    public MainMenu(HiddenNature hiddenNature){

        background = new Texture(Gdx.files.internal("background.png"));

        hn = hiddenNature;

        batch = hn.getBatch();

        entity1 = new Entity("button.png", 520f, 350f, 1);
        entity2 = new Entity("button.png", 520f, 250f, 2);
        entity3 = new Entity("button.png", 520f, 150f, 3);
        entity4 = new Entity("doge.png", 30, 420f, 4);

        mainStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        mainStage.addActor(entity1);
        mainStage.addActor(entity2);
        mainStage.addActor(entity3);
        mainStage.addActor(entity4);

        Gdx.input.setInputProcessor(mainStage);
    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: Gdx.app.log("entity", "no actions");
                break;

            case 1: hn.setScreen(new LevelSelect(hn));
                entity.resetAction();
                break;

            case 2:Gdx.app.log("entity", "credits screen");
                break;

            case 3:Gdx.app.log("entity", "quit game");
                break;

            case 4:Gdx.app.log("entity", "Mute");
                break;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.getBatch().begin();
        mainStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(),  hn.getWORLD_HEIGHT());
        mainStage.getBatch().end();

        mainStage.act(Gdx.graphics.getDeltaTime());

        mainStage.draw();

        getEntityID(entity1);
        getEntityID(entity2);
        getEntityID(entity3);
        getEntityID(entity4);
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
    public void hide(){
    }

}
