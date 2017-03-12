package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Level extends Actor{

    private int levelID;
    private String levelName;
    private Texture levelDiorama;
    private int entitiesFound;
    private Entity doge, doge2, doge3;
    private Array<Entity> objects;
    private Array<Entity> pauseObjects;
    private ObjectManager objectManager;
    private float zoom;
    private Vector3 camPos;

    public Level(int id, String name, String diorama, int found, float width, float height){

        levelID = id;
        objectManager = new ObjectManager(levelID);
        levelName = name;
        levelDiorama = new Texture(Gdx.files.internal(diorama));
        entitiesFound = found;
        setBounds(160, 90, width - 320, height - 180);

        objects = objectManager.getObjects();
        pauseObjects = objectManager.getPauseEntities();
    }
    public Array<Entity> getPauseObjects() {
        return pauseObjects;
    }

    public void setPauseObjects(Array<Entity> pauseObjects) {
        this.pauseObjects = pauseObjects;
    }

    public Array<Entity> getObjects() {
        return objects;
    }

    public void setObjects(Array<Entity> objects) {
        this.objects = objects;
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

    public String getLevelName(){
        return levelName;
    }

    public int getEntitiesFound(){
        return entitiesFound;
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
    
}
