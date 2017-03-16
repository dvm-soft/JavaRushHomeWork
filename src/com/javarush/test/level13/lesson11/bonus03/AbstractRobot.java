package com.javarush.test.level13.lesson11.bonus03;

public abstract class AbstractRobot implements Attackable, Defensable
{
    private static int hitCount;
    private static int hitCount2;

    public abstract String getName();

    public BodyPart attack()
    {
        BodyPart attackedBodyPart = null;
        hitCount = hitCount + 1;

        if (hitCount == 1)
        {
            attackedBodyPart = BodyPart.ARM;
        } else if (hitCount == 2)
        {
            attackedBodyPart = BodyPart.HEAD;
        } else if (hitCount == 3)
        {
            attackedBodyPart = BodyPart.LEG;
        } else if (hitCount == 4)
        {
            hitCount = 0;
            attackedBodyPart = BodyPart.CHEST;
        }
        return attackedBodyPart;
    }

    public BodyPart defense()
    {
        BodyPart defencedBodyPart = null;
        hitCount2 = hitCount2 + 1;

        if (hitCount2 == 1)
        {
            defencedBodyPart = BodyPart.HEAD;
        } else if (hitCount2 == 2)
        {
            defencedBodyPart = BodyPart.CHEST;
        } else if (hitCount2 == 3)
        {
            defencedBodyPart = BodyPart.LEG;
        } else if (hitCount2 == 4)
        {
            hitCount2 = 0;
            defencedBodyPart = BodyPart.ARM;
        }
        return defencedBodyPart;

    }

}
