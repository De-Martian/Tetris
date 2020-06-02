import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public Color[][] pallete = {{new Color(116,116,116),new Color(36,24,140),new Color(0,0,168),new Color(68,0,156),new Color(140,0,116),new Color(168,0,16),new Color(164,0,0),new Color(124,8,0),new Color(64,44,0),new Color(0,68,0),new Color(0,80,0),new Color(0,60,20),new Color(24,60,92),new Color(0,0,0),new Color(0,0,0),new Color(0,0,0)},
            {new Color(188,188,188),new Color(0,112,236),new Color(32,56,236),new Color(128,0,240),new Color(188,0,188),new Color(228,0,88),new Color(216,40,0),new Color(200,76,12),new Color(136,112,0),new Color(0,148,0),new Color(0,168,0),new Color(0,144,56),new Color(0,128,136),new Color(0,0,0),new Color(0,0,0),new Color(0,0,0)},
            {new Color(252,252,252),new Color(60,188,252),new Color(92,148,252),new Color(204,136,252),new Color(244,120,252),new Color(252,116,180),new Color(252,116,96),new Color(252,152,56),new Color(240,188,60),new Color(128,208,16),new Color(76,220,72),new Color(88,248,152),new Color(0,232,216),new Color(120,120,120),new Color(0,0,0),new Color(0,0,0)},
            {new Color(252,252,252),new Color(168,228,252),new Color(196,212,252),new Color(212,200,252),new Color(252,196,252),new Color(252,196,216),new Color(252,188,176),new Color(252,216,168),new Color(252,228,160),new Color(224,252,160),new Color(168,240,188),new Color(176,252,204),new Color(156,252,240),new Color(196,196,196),new Color(0,0,0),new Color(0,0,0)}};
    public int[][][][] rots = {{{{-1,0},{0,-1},{1,0},{0,0}},{{0,-1},{1,0},{0,1},{0,0}},{{1,0},{0,1},{-1,0},{0,0}},{{0,1},{-1,0},{0,-1},{0,0}}},
            {{{-1,0},{1,-1},{1,0},{0,0}},{{0,-1},{1,1},{0,1},{0,0}},{{1,0},{-1,1},{-1,0},{0,0}},{{0,1},{-1,-1},{0,-1},{0,0}}},
            {{{-1,0},{0,-1},{1,-1},{0,0}},{{0,-1},{1,0},{1,1},{0,0}},{{-1,0},{0,-1},{1,-1},{0,0}},{{0,-1},{1,0},{1,1},{0,0}}},
            {{{-1,0},{-1,-1},{0,-1},{0,0}},{{-1,0},{-1,-1},{0,-1},{0,0}},{{-1,0},{-1,-1},{0,-1},{0,0}},{{-1,0},{-1,-1},{0,-1},{0,0}}},
            {{{-1,-1},{0,-1},{1,0},{0,0}},{{1,-1},{1,0},{0,1},{0,0}},{{-1,-1},{0,-1},{1,0},{0,0}},{{1,-1},{1,0},{0,1},{0,0}}},
            {{{-1,0},{-1,-1},{1,0},{0,0}},{{0,-1},{1,-1},{0,1},{0,0}},{{1,0},{1,1},{-1,0},{0,0}},{{0,1},{-1,1},{0,-1},{0,0}}},
            {{{-2,0},{-1,0},{1,0},{0,0}},{{0,-1},{0,1},{0,2},{0,0}},{{-2,0},{-1,0},{1,0},{0,0}},{{0,-1},{0,1},{0,2},{0,0}}}};
            //Giant array for all piece rotations
    int[][] levelColors = {{2,1,1,2},{2,9,1,10},{2,4,1,4},{2,10,1,2},{2,11,1,5},{2,2,2,11},{0,0,1,6},{0,5,1,3},{1,6,1,2},{2,7,1,6}};
    game field = new game(); game nums = new game(); game side = new game(); game next = new game();
    int level; int lines; int score; int frameCounter = 1; int piece; int r = 0; int x = 5; int y = 2; int drop = 48; int das; int gameState = 1;
    int[] piececounter = new int[7]; int[] dropDelay = {48,43,38,33,28,23,18,13,8,6,5,5,5,4,4,4,3,3,3}; int[] top = {10000,7500,5000}; int[][] board = new int[10][22]; int[] Counters = new int[4];
    GreenfootImage[] square = new GreenfootImage[4]; GreenfootImage bg = new GreenfootImage(240,480); GreenfootImage numbers = new GreenfootImage(768,720); GreenfootImage[] digit = new GreenfootImage[10]; GreenfootImage[] digitred = new GreenfootImage[10];
    boolean[] inputs = new boolean[8]; boolean[] lastInputs = new boolean[8]; String[] keys = {"z","x","shift","enter","up","down","left","right"};
    int timer = 0; int levelup;
    //declare ALL the variables
    public MyWorld()
    {    
        super(768, 720, 1);
        level = 18;
        lines = 0;
        score = 0;
        addObject(field,408,384);
        addObject(nums,384,360);
        nums.setImage(numbers);
        addObject(side,106,435);
        addObject(next,622,382);
        levelup = level+1;
        if(level >= 10)
            levelup = 10+((level+1)&15)*((level+1)>>4);
        System.out.println(levelup);
        level--;
        incLevel();
        updateNext(7);
        GreenfootImage dig = new GreenfootImage("numbers.png");
        GreenfootImage dig2 = new GreenfootImage("numbred.png");
        for(int i = 0; i < 10; i++){
            digit[i] = new GreenfootImage(8,8);
            digit[i].drawImage(dig,-i*8,0);
            digit[i].scale(24,24);
            digitred[i] = new GreenfootImage(8,8);
            digitred[i].drawImage(dig2,-i*8,0);
            digitred[i].scale(24,24);
        }
        numb();
    }
    public long t = System.currentTimeMillis();
    public void act(){
        long t2 = System.currentTimeMillis();
        long diff = t2-t;
        if(diff*6 > (frameCounter+2)*100){
            t = t2-(frameCounter*100/6);
        }
        if(diff*6 >= frameCounter * 100){//only runs every 1/60 of a second
            frameCounter++;
            if(gameState == 0){
                menu();
            }
            else if(gameState == 1){
                inGame();
            }
            else if(gameState == 2){
                rocket();
            }
            //every frame things
            for(int i = 0; i < 8;i++)
                lastInputs[i] = inputs[i];
            for(int i = 0; i < 8; i++)
                inputs[i] = Greenfoot.isKeyDown(keys[i]);
        }
    }
    int ne = (int)(Math.random()*7);
    int moveCounter = 98;
    boolean moveDown = false;
    boolean newPiece = false;
    boolean letGo = true;
    boolean full = false;
    int[] line = new int[20];
    int dropPoints = 0;
    public void inGame(){
        if(timer > 0){
            timer--;
            if(full && timer < 26)
                clearLine(26-timer);
        }
        else{
            if(frameCounter == 2 || newPiece){
                piece = ne;
                spawnPiece();
                ne = (int)(Math.random()*8);
                if(ne == 7 || ne == piece) 
                    ne = (int)(Math.random()*7);
                updateNext(ne);
                newPiece = false;
                numb();
                letGo = false;
            }
            if(!inputs[5]){
                letGo = true;
                dropPoints = 0;
                if(inputs[6]){
                    if(das >= 16 || das == 0)
                        if(canMove(-1)){
                            move(-1);
                            das = lastInputs[6]? 10:0;
                        }
                        else
                            das = 15;
                    das++;
                }
                if(inputs[7]){
                    if(das >= 16 || das == 0)
                        if(canMove(1)){
                            move(1);
                            das = lastInputs[7]? 10:0;
                        }
                        else
                            das = 15;
                    das++;
                }
                if((!inputs[6]&&lastInputs[6]) || (!inputs[7]&&lastInputs[7]))
                    das = 0;
            }
            if(inputs[0] && !lastInputs[0] && canRotate(1))
                rotate(1);
            if(inputs[1] &&  !lastInputs[1] && canRotate(3))
                rotate(3);
            moveCounter--;
            if(inputs[5] && letGo && moveCounter > 1){
                moveCounter = 1;
                dropPoints++;
            }
            if(moveCounter <= 0){
                if(canMove(0))
                    move(0);
                else{
                    newPiece();
                    score += dropPoints;
                }
                if(level < 20)
                    moveCounter = dropDelay[level];
                else if(level < 29)
                    moveCounter = 2;
                else
                    moveCounter = 1;
            }
        }
        makeboard();
    }
    int menuState = 0;
    boolean a = true;
    public void menu(){
        if(inputs[3] && !lastInputs[3]){
            menuState++;
        }
        if(menuState == 2 && inputs[6])
            a = true;
        else if(menuState == 2 && inputs[7])
            a = false;
        if(menuState == 4)
            gameState = 1;
    }
    public void rocket(){
    }
    public void makeboard(){
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 20; j++)
                bg.drawImage(square[board[i][j+2]%4],i*24,j*24);
        field.setImage(bg);
    }
    public void updateNext(int t){
        int[][][] box = {{{1,0},{3,0},{5,0},{3,2}},{{1,0},{3,0},{5,0},{5,2}},{{1,0},{3,0},{5,2},{3,2}},
                {{2,0},{4,0},{2,2},{4,2}},{{1,2},{3,0},{5,0},{3,2}},{{1,0},{3,0},{5,0},{1,2}},{{0,1},{2,1},{4,1},{6,1}},{}}; 
        GreenfootImage temp = new GreenfootImage("empty.png");
        temp.scale(93,45);
        for(int[] pece: box[t])
            temp.drawImage(square[t%3+1],pece[0]*12,pece[1]*12);
        next.setImage(temp);
    }
    public void newPiece(){
        newPiece = true;
        timer = 17 - (y-2)/4;
        filled();
    }
    public void clearLine(int frame){
        if(frame%4 == 0)
            for(int i = 2; i < 22; i++){
                if(line[i-2] != 0 && frame <= 20){
                    board[4+frame/4][i] = 0;
                    board[5-frame/4][i] = 0;
                }
                if(line[i-2] != 0 && frame == 24){
                    moveDown(i);
                }
            }
    }
    public void moveDown(int lin){
        for(int i = lin; i > 1; i--)
            for(int j = 0; j < 10; j++)
                board[j][i] = i == 2? 0:board[j][i-1];
        if(lin == 2)
            line[19] = 1;
        full = false;
        if(lines >= levelup*10){
            levelup++;
            incLevel();
        }
    }
    public void incLevel(){
        level++;
        GreenfootImage counter = coloring(new GreenfootImage("piececount.png"));
        counter.scale(69,312);
        side.setImage(counter);
        GreenfootImage[] cells = {new GreenfootImage("empty.png"),new GreenfootImage("white.png"),new GreenfootImage("blue.png"),new GreenfootImage("red.png")};
        for(GreenfootImage cell: cells){        
            cell = coloring(cell);
            cell.scale(24,24);
        }
        square = cells;
        drop = (level < 19)? dropDelay[level]:(level<29)? 2:1;
    }
    public GreenfootImage coloring(GreenfootImage img){
        for(int i = 0; i < img.getWidth(); i++)
            for(int j = 0; j < img.getHeight(); j++)
                if(img.getColorAt(i,j).equals(Color.RED))
                    img.setColorAt(i,j,pallete[levelColors[level%10][0]][levelColors[level%10][1]]);
                else if(img.getColorAt(i,j).equals(Color.BLUE))
                    img.setColorAt(i,j,pallete[levelColors[level%10][2]][levelColors[level%10][3]]);
        return img;
    }
    public void drawNumber(int numer, int len, int x, int y){
        int temp = numer;
        for(int i = 0; i < len; i++){
            numbers.drawImage(digit[temp%10],x-24*i,y);
            temp /= 10;
        }
    }
    public void numb(){//draws all the numbers on screen
        drawNumber(score,6,696,192);
        drawNumber(top[0],6,696,120);
        drawNumber(lines,3,504,72);
        drawNumber(level,2,648,504);

        for(int i = 0; i < 7; i++){
            int temp = piececounter[i];
            for(int j = 0; j < 3; j++){
                numbers.drawImage(digitred[temp%10],192-24*j,288+48*i);
                temp /= 10;
            }
        }
        nums.setImage(numbers);
    }

    public void spawnPiece(){//spawns the current piece at the top
        x = 5;
        y = 2;
        r = 0;
        for(int[] i: rots[piece][r]){
            if(board[5+i[0]][2-i[1]] > 0)
                gameOver();
            board[5+i[0]][2-i[1]] = piece %3 +1;
        }
        piececounter[piece]++;
    }

    public boolean canMove(int dir){ 
        //tests if the piece can move left, right, or down
        boolean can = true;
        boolean in = false;
        for(int[] i:rots[piece][r]){
            board[x+i[0]][y-i[1]] = 0;
        }
        if(dir != 0){
            for(int[] i:rots[piece][r])
                if((x+i[0]+dir < 0) || (x+i[0]+dir > 9))
                    can = false;
                else if(board[x+i[0]+dir][y-i[1]] > 0)
                    can = false;
        }
        else{
            for(int[] i:rots[piece][r])
                if(y-i[1] == 21)
                    can = false;
                else if(board[x+i[0]][y-i[1]+1] > 0)
                    can = false;
        }
        for(int[] i:rots[piece][r])
            board[x+i[0]][y-i[1]] = piece%3+1;
        return can;
    }

    public void move(int dir){ //moves the piece
        for(int[] i:rots[piece][r])
            board[x+i[0]][y-i[1]] = 0;
        if(dir != 0){
            x += dir;
            for(int[] i:rots[piece][r])
                board[x+i[0]][y-i[1]] = piece%3+1;
        }
        else{
            y += 1;
            for(int[] i:rots[piece][r])
                board[x+i[0]][y-i[1]] = piece%3+1;
        }
    }

    public void gameOver(){
        System.out.println("Game Over");
        gameState = 2;
    }

    public boolean canRotate(int dir){
        boolean can = true;
        for(int[] i:rots[piece][r])
            board[x+i[0]][y-i[1]] -= 3;
        for(int[] i:rots[piece][(r+dir)%4])
            if((x+i[0] < 0) || (x+i[0] > 9) || (y-i[1] > 21))
                can = false;
            else if(board[x+i[0]][y-i[1]] > 0)
                can = false;
        for(int[] i:rots[piece][r])
            board[x+i[0]][y-i[1]] += 3;
        return can;
    }
    public void rotate(int dir){
        for(int[] i:rots[piece][r])
            board[x+i[0]][y-i[1]] = 0;
        r = (r+dir)%4;
        for(int[] i:rots[piece][r])
            board[x+i[0]][y-i[1]] = piece%3+1;
    }
    public void filled(){
        int[] scores = {0,40,100,300,1200};
        int re = 0;
        for(int i = 21; i > 1; i--){
            int prod = 1;
            for(int j = 0; j < 10; j++){
                prod *= board[j][i];
            }
            line[i-2] = prod;
            if(prod != 0){
                if(re == 0)
                    timer += 20;
                re++;
                full = true;
            }
        }
        score += scores[re]*(level+1);
    }
}
