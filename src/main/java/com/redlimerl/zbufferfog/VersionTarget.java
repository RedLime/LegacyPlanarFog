package com.redlimerl.zbufferfog;

public class VersionTarget {
    public Version start;
    public Version end;

    private VersionTarget(Version start, Version end) {
        this.start = start;
        this.end = end;
    }

    public static VersionTarget parse(String version) {
        String[] versions = version.split("to");
        Version end = null;
        if (versions.length == 2) {
            end = Version.parse(versions[1]);
        }
        return new VersionTarget(Version.parse(versions[0]), end);
    }

    public boolean intersects(int minor, int patch) {
        if (end == null) {
            return minor == start.minor && (start.patch == null || start.patch == patch);
        } else {
            return minor >= start.minor && minor <= end.minor &&
                    (start.patch == null || start.minor != minor || start.patch >= patch) &&
                    (end.patch == null || end.minor != minor || end.patch <= patch);
        }
    }

    public static class Version {
        public int minor;
        public Integer patch;

        private Version(int minor, Integer patch) {
            this.minor = minor;
            this.patch = patch;
        }

        public static Version parse(String version) {
            String[] versions = version.split("\\$");
            Integer end = null;
            if (versions.length == 2) {
                end = Integer.valueOf(versions[1]);
            }
            return new Version(Integer.parseInt(versions[0]), end);
        }
    }
}
