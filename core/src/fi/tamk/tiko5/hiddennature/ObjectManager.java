package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;



/**
 * Created by Jorix3 on 12.3.2017.
 */

public class ObjectManager {
    
    Array<Entity> entities, pauseEntities;
    Array<Texture> textures;

    public ObjectManager(int id){
        entities = new Array<Entity>();
        pauseEntities = new Array<Entity>();

        switch (id){
            
            case 1:
                entities.add(new Entity("doge.png", "dogesilu.png", 200f, 100f, -1, false));
                pauseEntities.add(new Entity("dogesilu.png", "doge.png", 10f, 10f, -1, false));
                entities.add(new Entity("fatso.png", "fatsosilu.png", 300f, 200f, -2, false));
                pauseEntities.add(new Entity("fatsosilu.png", "fatso.png", 50f, 10f, -2, false));
                entities.add(new Entity("doge.png", "dogesilu.png", 250f, 150f, -3, false));
                pauseEntities.add(new Entity("dogesilu.png", "doge.png", 90f, 10f, -3, false));
                entities.add(new Entity("doge.png", "dogesilu.png", 350f, 250f, -4, false));
                pauseEntities.add(new Entity("dogesilu.png", "doge.png", 130f, 10f, -4, false));
                break;
            
            
            
            case 2:
                entities.add(new Entity("doge.png", "dogesilu.png", 300f, 200f, -1, false));
                pauseEntities.add(new Entity("dogesilu.png", "doge.png", 200f, 100f, -1, false));
                
                break;

            case 3:
                entities.add(new Entity("doge.png", "dogesilu.png", 250f, 150f, -1, false));
                pauseEntities.add(new Entity("dogesilu.png", "doge.png", 200f, 100f, -1, false));
                
                break;
            
            
            
        }
    }

    public Array<Entity> getObjects() {
        return entities;
    }

    public Array<Entity> getPauseEntities() {
        return pauseEntities;
    }

}
