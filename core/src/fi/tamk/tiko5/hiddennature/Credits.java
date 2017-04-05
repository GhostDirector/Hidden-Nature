package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Credits implements Screen {
    private HiddenNature hn;
    private Texture background;
    private Stage creditsStage;
    private SpriteBatch batch;
    private Entity entity1, entity2, entity3, entity4, entity5;
    private BitmapFont font;
    private boolean gameIsOn;
    private String creditsText, leadText, programmingText, graphicsText, collabText;

    public Credits(HiddenNature hiddenNature){
        gameIsOn = true;
        hn = hiddenNature;
        background = new Texture(Gdx.files.internal("menu2.png"));
        batch = hn.getBatch();

        creditsText = hn.getLocalization().get("credits");
        leadText = hn.getLocalization().get("creLead");
        programmingText = hn.getLocalization().get("creProg");
        graphicsText = hn.getLocalization().get("creGraph");
        collabText =  hn.getLocalization().get("collabText");

        creditsStage = new Stage(new FitViewport(hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT()), batch);

        entity1 = new Entity("X.png", "xPushedButton.png", 690f, 390f, 1, true, 0.25f);
        entity2 = new Entity(hn.getLocalization().get("trashLogo"), hn.getLocalization().get("trashLogo"), 370f, 240f, 2, true, 0.15f);
        entity3 = new Entity(hn.getLocalization().get("tamkLogo"), hn.getLocalization().get("tamkLogo"), 40f, 40f, 3, true, 0.07f);
        entity4 = new Entity(hn.getLocalization().get("tikoLogo"), hn.getLocalization().get("tikoLogo"), 260f, 40f, 4, true, 0.35f);
        entity5 = new Entity(hn.getLocalization().get("vapriikkiLogo"), hn.getLocalization().get("vapriikkiLogo"), 520f, 40f, 5, true, 0.35f);

        creditsStage.addActor(entity1);
        creditsStage.addActor(entity2);
        creditsStage.addActor(entity3);
        creditsStage.addActor(entity4);
        creditsStage.addActor(entity5);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("comic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 22;
        param.color = Color.DARK_GRAY;
        param.borderWidth = 2;
        font = generator.generateFont(param);

        Gdx.input.setInputProcessor(creditsStage);

    }

    public void getEntityID(Entity entity){
        switch (entity.getAction()){

            case 0: //Gdx.app.log("entity", "no actions");
                entity.resetAction();
                break;

            case 1: hn.setScreen(new MainMenu(hn));
                entity.resetAction();
                break;

            case 2:
                //Gdx.net.openURI("http://<web page");
                entity.resetAction();
                break;

            case 3:
                Gdx.net.openURI(hn.getLocalization().get("tamkURL"));
                entity.resetAction();
                break;

            case 4:
                //Gdx.net.openURI("http://<web page");
                entity.resetAction();
                break;

            case 5:
                Gdx.net.openURI(hn.getLocalization().get("vapriikkiURL"));
                entity.resetAction();
                break;
        }
    }

    @Override
    public void render(float delta) {
        if (gameIsOn){
            creditsStage.getBatch().begin();
            creditsStage.getBatch().draw(background, 0, 0, hn.getWORLD_WIDTH(), hn.getWORLD_HEIGHT());
            font.draw(batch, creditsText, 100, 450);
            font.draw(batch, leadText + " Kaisa", 100, 400);
            font.draw(batch, programmingText +" Joni", 100, 370);
            font.draw(batch, programmingText + " Jyri", 100, 340);
            font.draw(batch, graphicsText + " Severi", 100, 310);

            font.draw(batch, collabText, 100, 220);
            
            creditsStage.getBatch().end();
            creditsStage.act(Gdx.graphics.getDeltaTime());
            creditsStage.draw();
            
            getEntityID(entity1);
            getEntityID(entity2);
            getEntityID(entity3);
            getEntityID(entity4);
            getEntityID(entity5);
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
        gameIsOn = false;
    }

    @Override
    public void resume() {
        gameIsOn = true;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
