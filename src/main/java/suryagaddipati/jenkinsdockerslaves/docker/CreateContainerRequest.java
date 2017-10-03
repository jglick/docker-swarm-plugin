/*
The MIT License (MIT)

Copyright (c) 2016, Groupon, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package suryagaddipati.jenkinsdockerslaves.docker;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class CreateContainerRequest {
    private final String[] Env;
    public String Image;
    public String[] Cmd;
    public HostConfig  HostConfig = new HostConfig();

    public CreateContainerRequest(String Image, String[] Cmd, String[] Env) {
        this.Image = Image;
        this.Cmd = Cmd;
        this.Env= Env;
    }



    public static  class  HostConfig{
        public boolean AutoRemove = true;
        public String[] Binds = new String[]{};
        public List<Mount> Mounts = new ArrayList<>();
        public void addCacheMount(String Source, String Target){
            Mounts.add(new Mount(Source,Target));
        }
    }
    private static class Mount {
        String Target;
        String Source;
        String Type = "volume";
        VolumeOptions VolumeOptions = new VolumeOptions();

        public Mount(String Source, String Target) {
            this.Source = Source;
            this.Target =Target;
        }

        private static class VolumeOptions{
            private static class DriverConfig{
                String Name = "cache-driver";
            }
        }
    }
}
