package fr.xilitra.higurashiuhc.config;

import fr.xilitra.higurashiuhc.utils.GSONSaver;

import java.io.File;

public class ConfigGestion {

    final File configContainer;
    Config config = Config.generateDefaultConfig("default");

    public ConfigGestion(File configContainer){
        this.configContainer = configContainer;
    }

    public Config getConfig(){
        return config;
    }

    private File getConfigFile(String configName){
        return new File(configContainer.getAbsolutePath()+File.separator+configName);
    }

    public boolean hasConfig(String preset){
        return getConfigFile(preset).exists();
    }

    public boolean hasConfig(File file){
        return file.exists();
    }

    public boolean useConfig(String preset){

        if(!hasConfig(preset))
            return false;

        File configFile = getConfigFile(preset);

        Config config = GSONSaver.loadGSON(configFile, Config.class);
        if(config == null)
            return false;

        this.config = config;
        return true;

    }

    public boolean saveConfig(Config config, boolean eraseOLD){
        File saveLocation = getConfigFile(config.getConfigName());
        if(hasConfig(saveLocation)){
            if(eraseOLD) {
                if(!saveLocation.delete())
                    return false;
            }else return false;
        }

        return GSONSaver.writeGSON(saveLocation, config);
    }

}
