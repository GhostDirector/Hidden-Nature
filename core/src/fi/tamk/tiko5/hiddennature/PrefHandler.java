package fi.tamk.tiko5.hiddennature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class PrefHandler {

    Preferences prefs;
    Level level;

    public PrefHandler(Level l){

        level = l;

        prefs = Gdx.app.getPreferences("savedLevel"+l.getLevelID());

    }
    
    public void save(){
        Array<Entity>entities = level.getFoundEntities();
        Array<Entity>originals = level.getFoundEntities();
        Array<Entity>pauseEntities = level.getFoundEntities();

        for(int i = 0; i < entities.size; i++){
            prefs.putString("Entity" + entities.get(i).getButtonID(),
                    entities.get(i).getButtonID() +
                            "::"+ entities.get(i).getOriginal().toString() +
                            "::"+ entities.get(i).getX() +
                            "::"+ entities.get(i).getY() +
                            "::"+ entities.get(i).isFound());
            prefs.flush();
        }
        for(int i = 0; i < originals.size; i++){
            prefs.putString("originals" + originals.get(i).getButtonID(),
                    originals.get(i).getButtonID() +
                            "::"+ originals.get(i).getOriginal().toString() +
                            "::"+ originals.get(i).getX() +
                            "::"+ originals.get(i).getY() +
                            "::"+ originals.get(i).isFound());
            prefs.flush();
        }
        for(int i = 0; i < pauseEntities.size; i++){
            prefs.putString("pauseEntities" + pauseEntities.get(i).getButtonID(),
                    pauseEntities.get(i).getButtonID() +
                            "::"+ pauseEntities.get(i).getOriginal().toString() +
                            "::"+ pauseEntities.get(i).getX() +
                            "::"+ pauseEntities.get(i).getY() +
                            "::"+ pauseEntities.get(i).isFound());
            prefs.flush();
        }
    }

    public Array<Entity> load(){
        return null;
    }
}

