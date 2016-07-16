package GUI;/*   Copyright 2015 Matthew Rogers "BossLetsPlays"
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
import java.awt.*;

/**
 * Created by Future on 7/6/2016.
 */
public class Animation {

    private int       count;
    private int       index;
    private int       speed;
    private int       numFrames;
    private Texture   currentFrame;
    private Texture[] frames;

    public Animation(int speed, Texture... frames) {
        this.speed = speed;
        this.frames = frames;
        this.numFrames = frames.length;
    }

    private void nextFrame() {
        currentFrame = frames[index++];
        if (index >= numFrames)
            index = 0;
    }

    public void run() {
        // Animation is faster the lower speed is
        count++;
        if(count > speed){
            count = 0;
            nextFrame();
        }

        // Animation is faster the greater speed is
//        count += speed;
//        if (count > 100) { //where 100 is some constant, you choose!
//            count = 0;
//            nextFrame();
//        }
    }

    public void render(Graphics g, int x, int y){
        if(currentFrame != null)
            currentFrame.render(g, x, y);
    }

}
