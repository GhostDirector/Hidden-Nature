package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Level extends Actor{
    private int levelID;
    private String levelName, dioString;
    private Texture levelDiorama;
    private int entitiesFound;
    private Array<Entity>originals = new Array<Entity>();
    private Array<Entity>silhouettes = new Array<Entity>();
    private Array<Entity>entities = new Array<Entity>();
    private Vector3 camPos;
    private float zoom;
    private ObjectManager objectManager;
    private boolean reset;
    
    public Level(int id, String name, String diorama, int found, float width, float height, boolean reset){
        this.reset = reset;
        dioString = diorama;
        levelID = id;
        levelName = name;
        levelDiorama = new Texture(Gdx.files.internal(diorama));
        entitiesFound = found;
        setBounds(160, 90, width - 320, height - 180);
        objectManager = new ObjectManager(this, this.reset);
        entities = objectManager.getEntities();

        for (int i = 0; i < entities.size; i++){
            Gdx.app.log("entities","");
            Gdx.app.log("id "+ i +" :", ""+entities.get(i).getButtonID());
            Gdx.app.log("png "+ i +" :", ""+entities.get(i).getOriginal());
            Gdx.app.log("x "+ i +" :", ""+entities.get(i).getX());
            Gdx.app.log("y "+ i +" :", ""+entities.get(i).getY());
            Gdx.app.log("scale "+ i +" :", ""+entities.get(i).getScale());
            Gdx.app.log("found "+ i +" :", ""+entities.get(i).isFound());
        }

        originals = objectManager.getOriginals();

        for (int j = 0; j < originals.size; j++){
            Gdx.app.log("originals","");
            Gdx.app.log("id "+ j +" :", ""+originals.get(j).getButtonID());
            Gdx.app.log("png "+ j +" :", ""+originals.get(j).getOriginal());
            Gdx.app.log("x "+ j +" :", ""+originals.get(j).getX());
            Gdx.app.log("y "+ j +" :", ""+originals.get(j).getY());
            Gdx.app.log("scale "+ j +" :", ""+originals.get(j).getScale());
            Gdx.app.log("found "+ j +" :", ""+originals.get(j).isFound());
        }

        silhouettes = objectManager.getSilhouettes();

        for (int k = 0; k < silhouettes.size; k++){
            Gdx.app.log("silhouettes","");
            Gdx.app.log("id "+ k +" :", ""+silhouettes.get(k).getButtonID());
            Gdx.app.log("png "+ k +" :", ""+silhouettes.get(k).getOriginal());
            Gdx.app.log("x "+ k +" :", ""+silhouettes.get(k).getX());
            Gdx.app.log("y "+ k +" :", ""+silhouettes.get(k).getY());
            Gdx.app.log("scale "+ k +" :", ""+silhouettes.get(k).getScale());
            Gdx.app.log("found "+ k +" :", ""+silhouettes.get(k).isFound());
        }
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

    public int getEntitiesFound(){
        return entitiesFound;
    }
}
