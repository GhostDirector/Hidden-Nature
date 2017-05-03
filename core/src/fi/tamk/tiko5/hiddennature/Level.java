package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;

public class Level extends Actor{
    private int levelID;
    private String levelName;
    private Texture levelDiorama;
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private Array<Entity>entities = new Array<Entity>();
    private Vector3 camPos;
    private float zoom;
    private ObjectManager objectManager;

    public Level(int id, String name, String diorama, float width, float height){
        levelID = id;
        levelName = name;
        levelDiorama = new Texture(Gdx.files.internal(diorama));
        setBounds(160, 90, width - 320, height - 180);
        objectManager = new ObjectManager(this);

        entities = objectManager.getEntities();
        originals = objectManager.getOriginals();
        silhouettes = objectManager.getSilhouettes();

        addListener(new InputListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(levelDiorama,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                levelDiorama.getWidth(), levelDiorama.getHeight(), false, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    public Array<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Array<Entity> foundEntities) {
        this.entities = foundEntities;
    }

    public Array<Entity> getSilhouettes() {
        return silhouettes;
    }

    public void setSilhouettes(Array<Entity> pauseSilhouettes) {
        this.silhouettes = pauseSilhouettes;
    }

    public Array<Entity> getOriginals() {
        return originals;
    }

    public void setOriginals(Array<Entity> originals) {
        this.originals = originals;
    }

    public void setZoom(float z){
        zoom = z;
    }

    public float getZoom(){
        return zoom;
    }

    public void setCamPos(Vector3 pos){
        camPos = pos;
    }

    public Vector3 getCamPos(){
        return camPos;
    }

    public Texture getLevelDiorama(){
        return levelDiorama;
    }

    public int getLevelID(){
        return levelID;
    }

}
