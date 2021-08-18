package fr.xilitra.higurashiuhc.utils;

public class Utility {

    public static String FindPackage(String className) {

        final Package[] packages = Package.getPackages();

        for (final Package p : packages) {
            final String pack = p.getName();
            final String tentative = pack + "." + className;
            try {
                Class.forName(tentative);
            } catch (final ClassNotFoundException e) {
                continue;
            }
            return pack;
        }

        return null;

    }

}
