package com.mcjty.tutpower.cables.client;

import com.mcjty.tutpower.cables.ConnectorType;

import java.util.HashMap;
import java.util.Map;

import static com.mcjty.tutpower.cables.ConnectorType.NONE;

public class CablePatterns {

    static final Map<Pattern, QuadSetting> PATTERNS = new HashMap<>();

    public static QuadSetting findPattern(ConnectorType s1, ConnectorType s2, ConnectorType s3, ConnectorType s4) {
        return PATTERNS.get(new Pattern(s1 != NONE, s2 != NONE, s3 != NONE, s4 != NONE));
    }

    public enum SpriteIdx {
        SPRITE_NONE,
        SPRITE_END,
        SPRITE_STRAIGHT,
        SPRITE_CORNER,
        SPRITE_THREE,
        SPRITE_CROSS
    }

    public static record QuadSetting(SpriteIdx sprite, int rotation) {
    }

    public static record Pattern(boolean s1, boolean s2, boolean s3, boolean s4) {
    }
}
