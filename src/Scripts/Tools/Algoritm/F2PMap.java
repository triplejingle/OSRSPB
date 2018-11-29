package Scripts.Tools.Algoritm;

import Scripts.Tools.Factory.VertexGenerator;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;


public class F2PMap extends ClientAccessor{
    Dijkstra dijkstra = new Dijkstra();
    VertexGenerator vertexGenerator = new VertexGenerator(ctx);
    Tile[] LumbridgeCastleTo1 = {new Tile(3214, 3219, 0),new Tile(3215, 3219, 0),new Tile(3216, 3219, 0),new Tile(3217, 3219, 0),new Tile(3218, 3219, 0),new Tile(3219, 3219, 0),new Tile(3220, 3219, 0),new Tile(3221, 3219, 0),new Tile(3222, 3219, 0),new Tile(3223, 3219, 0),new Tile(3224, 3219, 0),new Tile(3225, 3219, 0),new Tile(3226, 3219, 0),new Tile(3227, 3219, 0),new Tile(3228, 3219, 0),new Tile(3229, 3219, 0),new Tile(3230, 3219, 0),new Tile(3231, 3219, 0),new Tile(3232, 3219, 0),new Tile(3233, 3219, 0),new Tile(3234, 3219, 0),new Tile(3235, 3219, 0)};
    Tile[] Lumbridge11To1 = {new Tile(3215, 3219, 0),new Tile(3217, 3219, 0),new Tile(3219, 3219, 0),new Tile(3221, 3219, 0),new Tile(3223, 3219, 0),new Tile(3225, 3219, 0),new Tile(3227, 3219, 0),new Tile(3229, 3219, 0),new Tile(3231, 3219, 0),new Tile(3233, 3219, 0),new Tile(3235, 3219, 0),new Tile(3235, 3217, 0),new Tile(3235, 3215, 0),new Tile(3235, 3213, 0),new Tile(3235, 3211, 0),new Tile(3235, 3209, 0),new Tile(3235, 3207, 0),new Tile(3235, 3205, 0),new Tile(3235, 3203, 0),new Tile(3235, 3201, 0),new Tile(3235, 3202, 0),new Tile(3235, 3204, 0),new Tile(3235, 3206, 0),new Tile(3235, 3208, 0),new Tile(3235, 3210, 0),new Tile(3235, 3212, 0),new Tile(3235, 3214, 0),new Tile(3235, 3216, 0),new Tile(3235, 3218, 0)};
    Tile[] LumbridgeCastle1To2 = {new Tile(3235, 3209, 0),new Tile(3235, 3219, 0),new Tile(3236, 3219, 0),new Tile(3235, 3220, 0),new Tile(3235, 3221, 0),new Tile(3235, 3222, 0),new Tile(3235, 3223, 0),new Tile(3235, 3224, 0),new Tile(3235, 3225, 0)};
    Tile[] Lumbridge2To3 = {new Tile(3235, 3219, 0),new Tile(3235, 3220, 0),new Tile(3235, 3222, 0),new Tile(3235, 3224, 0),new Tile(3235, 3225, 0),new Tile(3236, 3225, 0),new Tile(3237, 3225, 0),new Tile(3238, 3225, 0),new Tile(3239, 3225, 0),new Tile(3240, 3225, 0),new Tile(3241, 3225, 0),new Tile(3242, 3225, 0),new Tile(3243, 3225, 0),new Tile(3244, 3225, 0),new Tile(3245, 3225, 0),new Tile(3246, 3225, 0),new Tile(3247, 3225, 0),new Tile(3248, 3225, 0),new Tile(3249, 3225, 0),new Tile(3250, 3225, 0),new Tile(3251, 3225, 0),new Tile(3252, 3225, 0),new Tile(3253, 3225, 0),new Tile(3254, 3225, 0),new Tile(3254, 3226, 0),new Tile(3255, 3226, 0),new Tile(3256, 3227, 0),new Tile(3257, 3227, 0),new Tile(3258, 3227, 0),new Tile(3259, 3228, 0)};
    Tile[] Lumbridge3To4 = {new Tile(3259, 3234, 0),new Tile(3259, 3230, 0),new Tile(3259, 3228, 0),new Tile(3259, 3229, 0),new Tile(3259, 3231, 0),new Tile(3259, 3232, 0),new Tile(3259, 3233, 0),new Tile(3259, 3235, 0),new Tile(3259, 3236, 0),new Tile(3259, 3237, 0),new Tile(3259, 3238, 0),new Tile(3259, 3239, 0),new Tile(3259, 3240, 0),new Tile(3259, 3241, 0),new Tile(3259, 3242, 0),new Tile(3259, 3243, 0),new Tile(3259, 3244, 0),new Tile(3259, 3245, 0),new Tile(3259, 3246, 0),new Tile(3258, 3247, 0),new Tile(3257, 3247, 0),new Tile(3257, 3248, 0),new Tile(3256, 3248, 0),new Tile(3256, 3249, 0),new Tile(3255, 3249, 0),new Tile(3254, 3250, 0),new Tile(3253, 3250, 0),new Tile(3253, 3251, 0),new Tile(3252, 3252, 0),new Tile(3252, 3253, 0),new Tile(3251, 3253, 0),new Tile(3251, 3254, 0),new Tile(3250, 3254, 0),new Tile(3250, 3255, 0),new Tile(3250, 3256, 0),new Tile(3250, 3257, 0),new Tile(3250, 3258, 0),new Tile(3250, 3259, 0),new Tile(3250, 3260, 0),new Tile(3250, 3261, 0),new Tile(3250, 3262, 0)};
    Tile[] Lumbridge4To5 = {new Tile(3259, 3230, 0),new Tile(3259, 3229, 0),new Tile(3259, 3232, 0),new Tile(3259, 3235, 0),new Tile(3259, 3237, 0),new Tile(3259, 3239, 0),new Tile(3259, 3241, 0),new Tile(3259, 3243, 0),new Tile(3259, 3245, 0),new Tile(3258, 3247, 0),new Tile(3257, 3248, 0),new Tile(3256, 3249, 0),new Tile(3254, 3250, 0),new Tile(3253, 3251, 0),new Tile(3252, 3253, 0),new Tile(3251, 3254, 0),new Tile(3250, 3255, 0),new Tile(3250, 3257, 0),new Tile(3250, 3259, 0),new Tile(3250, 3261, 0),new Tile(3250, 3262, 0),new Tile(3250, 3263, 0),new Tile(3250, 3264, 0),new Tile(3250, 3265, 0),new Tile(3250, 3266, 0),new Tile(3250, 3267, 0),new Tile(3249, 3267, 0),new Tile(3248, 3268, 0),new Tile(3247, 3269, 0),new Tile(3247, 3270, 0),new Tile(3247, 3271, 0),new Tile(3247, 3272, 0),new Tile(3246, 3273, 0),new Tile(3246, 3274, 0),new Tile(3245, 3274, 0),new Tile(3244, 3274, 0),new Tile(3243, 3274, 0),new Tile(3243, 3275, 0),new Tile(3242, 3275, 0),new Tile(3242, 3276, 0),new Tile(3241, 3276, 0),new Tile(3241, 3277, 0),new Tile(3240, 3278, 0),new Tile(3240, 3279, 0),new Tile(3240, 3280, 0),new Tile(3240, 3281, 0),new Tile(3240, 3282, 0),new Tile(3239, 3283, 0),new Tile(3239, 3284, 0),new Tile(3239, 3285, 0),new Tile(3238, 3286, 0),new Tile(3238, 3287, 0),new Tile(3238, 3288, 0),new Tile(3238, 3289, 0),new Tile(3238, 3290, 0),new Tile(3238, 3291, 0),new Tile(3238, 3292, 0),new Tile(3238, 3293, 0),new Tile(3238, 3294, 0),new Tile(3238, 3295, 0),new Tile(3238, 3296, 0),new Tile(3238, 3297, 0),new Tile(3238, 3298, 0),new Tile(3238, 3299, 0),new Tile(3238, 3300, 0),new Tile(3238, 3301, 0),new Tile(3238, 3302, 0),new Tile(3238, 3303, 0),new Tile(3238, 3304, 0),new Tile(3239, 3305, 0),new Tile(3240, 3306, 0)};
    Tile[] Lumbridge4To7 = {new Tile(3259, 3229, 0),new Tile(3259, 3235, 0),new Tile(3259, 3239, 0),new Tile(3259, 3243, 0),new Tile(3258, 3247, 0),new Tile(3256, 3249, 0),new Tile(3253, 3251, 0),new Tile(3251, 3254, 0),new Tile(3250, 3257, 0),new Tile(3250, 3261, 0),new Tile(3250, 3263, 0),new Tile(3250, 3265, 0),new Tile(3250, 3267, 0),new Tile(3248, 3268, 0),new Tile(3247, 3270, 0),new Tile(3247, 3272, 0),new Tile(3246, 3274, 0),new Tile(3244, 3274, 0),new Tile(3243, 3275, 0),new Tile(3242, 3276, 0),new Tile(3241, 3277, 0),new Tile(3240, 3279, 0),new Tile(3240, 3281, 0),new Tile(3239, 3283, 0),new Tile(3239, 3285, 0),new Tile(3238, 3287, 0),new Tile(3238, 3289, 0),new Tile(3238, 3291, 0),new Tile(3238, 3293, 0),new Tile(3238, 3295, 0),new Tile(3238, 3297, 0),new Tile(3238, 3299, 0),new Tile(3238, 3301, 0),new Tile(3238, 3303, 0),new Tile(3239, 3305, 0),new Tile(3240, 3305, 0),new Tile(3240, 3303, 0),new Tile(3239, 3297, 0),new Tile(3239, 3295, 0),new Tile(3239, 3293, 0),new Tile(3239, 3291, 0),new Tile(3239, 3289, 0),new Tile(3239, 3287, 0),new Tile(3239, 3282, 0),new Tile(3239, 3280, 0),new Tile(3239, 3278, 0),new Tile(3243, 3276, 0),new Tile(3245, 3273, 0),new Tile(3245, 3271, 0),new Tile(3247, 3268, 0),new Tile(3249, 3266, 0),new Tile(3250, 3262, 0),new Tile(3249, 3262, 0),new Tile(3248, 3262, 0),new Tile(3247, 3262, 0),new Tile(3246, 3262, 0),new Tile(3245, 3262, 0),new Tile(3244, 3262, 0),new Tile(3243, 3262, 0),new Tile(3242, 3262, 0),new Tile(3241, 3262, 0),new Tile(3240, 3262, 0),new Tile(3239, 3262, 0),new Tile(3238, 3262, 0),new Tile(3237, 3262, 0),new Tile(3236, 3262, 0),new Tile(3235, 3262, 0),new Tile(3234, 3262, 0),new Tile(3233, 3262, 0),new Tile(3232, 3262, 0),new Tile(3231, 3262, 0),new Tile(3230, 3262, 0),new Tile(3229, 3262, 0),new Tile(3228, 3262, 0),new Tile(3227, 3262, 0),new Tile(3226, 3262, 0),new Tile(3225, 3262, 0),new Tile(3224, 3262, 0),new Tile(3223, 3262, 0),new Tile(3222, 3262, 0),new Tile(3221, 3262, 0),new Tile(3220, 3262, 0),new Tile(3219, 3262, 0),new Tile(3218, 3262, 0),new Tile(3217, 3262, 0)};
    Tile[] LumbridgeGToF = {new Tile(3259, 3243, 0),new Tile(3251, 3254, 0),new Tile(3250, 3265, 0),new Tile(3247, 3272, 0),new Tile(3242, 3276, 0),new Tile(3239, 3283, 0),new Tile(3238, 3291, 0),new Tile(3238, 3299, 0),new Tile(3240, 3305, 0),new Tile(3239, 3293, 0),new Tile(3239, 3282, 0),new Tile(3245, 3273, 0),new Tile(3250, 3262, 0),new Tile(3246, 3262, 0),new Tile(3242, 3262, 0),new Tile(3238, 3262, 0),new Tile(3234, 3262, 0),new Tile(3230, 3262, 0),new Tile(3226, 3262, 0),new Tile(3222, 3262, 0),new Tile(3218, 3262, 0),new Tile(3215, 3262, 0),new Tile(3217, 3262, 0),new Tile(3217, 3261, 0),new Tile(3217, 3260, 0),new Tile(3217, 3259, 0),new Tile(3217, 3258, 0),new Tile(3217, 3257, 0),new Tile(3217, 3256, 0),new Tile(3217, 3255, 0),new Tile(3217, 3254, 0),new Tile(3217, 3253, 0),new Tile(3217, 3252, 0),new Tile(3217, 3251, 0),new Tile(3217, 3250, 0),new Tile(3217, 3249, 0),new Tile(3217, 3248, 0),new Tile(3217, 3247, 0),new Tile(3218, 3246, 0),new Tile(3218, 3245, 0),new Tile(3218, 3244, 0),new Tile(3219, 3243, 0),new Tile(3219, 3242, 0),new Tile(3219, 3241, 0),new Tile(3220, 3240, 0),new Tile(3221, 3239, 0)};
    Tile[] LumbridgeFToD = {new Tile(3247, 3272, 0),new Tile(3238, 3299, 0),new Tile(3245, 3273, 0),new Tile(3238, 3262, 0),new Tile(3222, 3262, 0),new Tile(3217, 3261, 0),new Tile(3217, 3257, 0),new Tile(3217, 3253, 0),new Tile(3217, 3249, 0),new Tile(3218, 3245, 0),new Tile(3219, 3241, 0),new Tile(3221, 3239, 0),new Tile(3222, 3239, 0),new Tile(3223, 3239, 0),new Tile(3224, 3238, 0),new Tile(3225, 3237, 0),new Tile(3226, 3236, 0),new Tile(3227, 3235, 0),new Tile(3228, 3234, 0),new Tile(3229, 3233, 0),new Tile(3230, 3232, 0),new Tile(3230, 3231, 0),new Tile(3231, 3230, 0),new Tile(3232, 3229, 0),new Tile(3233, 3228, 0),new Tile(3234, 3227, 0),new Tile(3235, 3227, 0),new Tile(3236, 3227, 0),new Tile(3236, 3226, 0)};
    Tile[] LumbridgeToDraynorVillage2 = {new Tile(3221, 3218, 0),new Tile(3220, 3218, 0),new Tile(3219, 3218, 0),new Tile(3218, 3218, 0),new Tile(3217, 3218, 0),new Tile(3216, 3218, 0),new Tile(3215, 3218, 0),new Tile(3215, 3217, 0),new Tile(3215, 3216, 0),new Tile(3215, 3215, 0),new Tile(3215, 3214, 0),new Tile(3215, 3213, 0),new Tile(3215, 3212, 0),new Tile(3215, 3211, 0),new Tile(3214, 3211, 0),new Tile(3213, 3211, 0),new Tile(3212, 3211, 0),new Tile(3211, 3211, 0),new Tile(3210, 3211, 0),new Tile(3209, 3211, 0),new Tile(3208, 3211, 0),new Tile(3208, 3212, 0),new Tile(3207, 3213, 0),new Tile(3206, 3214, 0),new Tile(3205, 3214, 0),new Tile(3204, 3214, 0),new Tile(3203, 3214, 0),new Tile(3203, 3215, 0),new Tile(3203, 3216, 0),new Tile(3203, 3217, 0),new Tile(3202, 3218, 0),new Tile(3201, 3218, 0),new Tile(3200, 3218, 0),new Tile(3199, 3218, 0),new Tile(3198, 3218, 0),new Tile(3197, 3217, 0),new Tile(3196, 3216, 0),new Tile(3195, 3216, 0),new Tile(3194, 3216, 0),new Tile(3193, 3216, 0),new Tile(3192, 3216, 0),new Tile(3191, 3216, 0),new Tile(3190, 3216, 0),new Tile(3189, 3216, 0),new Tile(3188, 3216, 0),new Tile(3187, 3216, 0),new Tile(3186, 3216, 0),new Tile(3185, 3217, 0),new Tile(3184, 3217, 0),new Tile(3183, 3217, 0),new Tile(3182, 3217, 0),new Tile(3181, 3217, 0),new Tile(3180, 3217, 0),new Tile(3180, 3218, 0),new Tile(3179, 3218, 0),new Tile(3178, 3218, 0),new Tile(3177, 3218, 0),new Tile(3176, 3218, 0),new Tile(3175, 3218, 0),new Tile(3174, 3218, 0),new Tile(3173, 3218, 0),new Tile(3172, 3218, 0),new Tile(3171, 3218, 0),new Tile(3170, 3218, 0),new Tile(3169, 3217, 0),new Tile(3168, 3217, 0),new Tile(3167, 3217, 0),new Tile(3166, 3217, 0),new Tile(3165, 3218, 0),new Tile(3164, 3218, 0),new Tile(3163, 3218, 0),new Tile(3162, 3218, 0),new Tile(3161, 3218, 0),new Tile(3160, 3219, 0),new Tile(3160, 3220, 0),new Tile(3160, 3221, 0),new Tile(3159, 3222, 0),new Tile(3158, 3223, 0),new Tile(3157, 3223, 0),new Tile(3156, 3223, 0),new Tile(3155, 3223, 0),new Tile(3154, 3223, 0),new Tile(3153, 3222, 0),new Tile(3152, 3222, 0),new Tile(3151, 3222, 0),new Tile(3150, 3222, 0),new Tile(3149, 3222, 0),new Tile(3148, 3223, 0),new Tile(3147, 3224, 0),new Tile(3146, 3224, 0),new Tile(3145, 3224, 0),new Tile(3144, 3224, 0),new Tile(3143, 3225, 0),new Tile(3142, 3225, 0),new Tile(3141, 3225, 0),new Tile(3140, 3226, 0),new Tile(3139, 3226, 0),new Tile(3138, 3226, 0),new Tile(3137, 3226, 0),new Tile(3136, 3226, 0),new Tile(3135, 3226, 0),new Tile(3134, 3226, 0),new Tile(3133, 3226, 0),new Tile(3132, 3226, 0),new Tile(3131, 3226, 0),new Tile(3130, 3226, 0),new Tile(3129, 3225, 0),new Tile(3128, 3224, 0),new Tile(3127, 3224, 0),new Tile(3126, 3224, 0),new Tile(3125, 3224, 0),new Tile(3124, 3224, 0),new Tile(3123, 3224, 0),new Tile(3122, 3223, 0),new Tile(3121, 3222, 0),new Tile(3120, 3221, 0),new Tile(3119, 3220, 0),new Tile(3119, 3219, 0),new Tile(3118, 3218, 0),new Tile(3117, 3218, 0),new Tile(3116, 3218, 0),new Tile(3116, 3219, 0),new Tile(3116, 3220, 0),new Tile(3116, 3221, 0),new Tile(3115, 3221, 0),new Tile(3114, 3222, 0)};

    Tile[] DraynorVillage2ToDraynorVillage = {new Tile(3215, 3217, 0),new Tile(3213, 3211, 0),new Tile(3206, 3214, 0),new Tile(3201, 3218, 0),new Tile(3193, 3216, 0),new Tile(3185, 3217, 0),new Tile(3178, 3218, 0),new Tile(3170, 3218, 0),new Tile(3162, 3218, 0),new Tile(3156, 3223, 0),new Tile(3148, 3223, 0),new Tile(3140, 3226, 0),new Tile(3132, 3226, 0),new Tile(3124, 3224, 0),new Tile(3117, 3218, 0),new Tile(3114, 3222, 0),new Tile(3114, 3223, 0),new Tile(3113, 3224, 0),new Tile(3113, 3225, 0),new Tile(3113, 3226, 0),new Tile(3113, 3227, 0),new Tile(3113, 3228, 0),new Tile(3113, 3229, 0),new Tile(3112, 3230, 0),new Tile(3111, 3231, 0),new Tile(3110, 3232, 0),new Tile(3110, 3233, 0),new Tile(3109, 3234, 0),new Tile(3109, 3235, 0),new Tile(3108, 3235, 0),new Tile(3108, 3236, 0),new Tile(3108, 3237, 0),new Tile(3108, 3238, 0),new Tile(3108, 3239, 0),new Tile(3108, 3240, 0),new Tile(3108, 3241, 0),new Tile(3108, 3242, 0),new Tile(3107, 3242, 0),new Tile(3107, 3243, 0),new Tile(3106, 3243, 0),new Tile(3106, 3244, 0),new Tile(3105, 3244, 0),new Tile(3105, 3245, 0),new Tile(3105, 3246, 0),new Tile(3105, 3247, 0),new Tile(3105, 3248, 0),new Tile(3105, 3249, 0),new Tile(3105, 3250, 0)};
    Tile[] DraynorVillageTo1 = {new Tile(3114, 3222, 0),new Tile(3108, 3237, 0),new Tile(3105, 3250, 0),new Tile(3105, 3251, 0),new Tile(3105, 3252, 0),new Tile(3105, 3253, 0),new Tile(3104, 3254, 0),new Tile(3104, 3255, 0),new Tile(3104, 3256, 0),new Tile(3104, 3257, 0),new Tile(3104, 3258, 0),new Tile(3104, 3259, 0),new Tile(3104, 3260, 0),new Tile(3104, 3261, 0),new Tile(3104, 3262, 0),new Tile(3104, 3263, 0),new Tile(3104, 3264, 0),new Tile(3104, 3265, 0),new Tile(3104, 3266, 0),new Tile(3104, 3267, 0),new Tile(3104, 3268, 0),new Tile(3104, 3269, 0),new Tile(3104, 3270, 0),new Tile(3104, 3271, 0),new Tile(3104, 3272, 0),new Tile(3104, 3273, 0),new Tile(3104, 3274, 0),new Tile(3104, 3275, 0),new Tile(3104, 3276, 0),new Tile(3105, 3277, 0),new Tile(3106, 3278, 0),new Tile(3107, 3279, 0),new Tile(3108, 3280, 0),new Tile(3109, 3281, 0),new Tile(3109, 3282, 0),new Tile(3109, 3283, 0),new Tile(3109, 3284, 0),new Tile(3109, 3285, 0),new Tile(3109, 3286, 0),new Tile(3109, 3287, 0),new Tile(3109, 3288, 0),new Tile(3109, 3289, 0),new Tile(3109, 3290, 0),new Tile(3109, 3291, 0),new Tile(3109, 3292, 0),new Tile(3109, 3293, 0),new Tile(3110, 3294, 0)};
    Tile[] DraynorVillage1ToBarbarianVillage1 = {new Tile(3099, 3420, 0),new Tile(3099, 3419, 0),new Tile(3098, 3418, 0),new Tile(3098, 3417, 0),new Tile(3098, 3416, 0),new Tile(3098, 3415, 0),new Tile(3098, 3414, 0),new Tile(3099, 3414, 0),new Tile(3099, 3413, 0),new Tile(3099, 3412, 0),new Tile(3099, 3411, 0),new Tile(3099, 3410, 0),new Tile(3099, 3409, 0),new Tile(3099, 3408, 0),new Tile(3099, 3407, 0),new Tile(3099, 3406, 0),new Tile(3099, 3405, 0),new Tile(3099, 3404, 0),new Tile(3098, 3403, 0),new Tile(3098, 3402, 0),new Tile(3098, 3401, 0),new Tile(3098, 3400, 0),new Tile(3098, 3399, 0),new Tile(3098, 3398, 0),new Tile(3099, 3398, 0),new Tile(3100, 3398, 0),new Tile(3101, 3397, 0),new Tile(3102, 3397, 0),new Tile(3102, 3396, 0),new Tile(3103, 3395, 0),new Tile(3104, 3395, 0),new Tile(3105, 3394, 0),new Tile(3106, 3394, 0),new Tile(3107, 3394, 0),new Tile(3108, 3394, 0),new Tile(3109, 3394, 0),new Tile(3110, 3393, 0),new Tile(3111, 3392, 0),new Tile(3112, 3392, 0),new Tile(3112, 3391, 0),new Tile(3113, 3391, 0),new Tile(3113, 3390, 0),new Tile(3114, 3390, 0),new Tile(3114, 3389, 0),new Tile(3114, 3388, 0),new Tile(3114, 3387, 0),new Tile(3115, 3387, 0),new Tile(3116, 3387, 0),new Tile(3116, 3386, 0),new Tile(3117, 3386, 0),new Tile(3117, 3385, 0),new Tile(3118, 3385, 0),new Tile(3119, 3385, 0),new Tile(3120, 3384, 0),new Tile(3121, 3384, 0),new Tile(3121, 3383, 0),new Tile(3122, 3382, 0),new Tile(3122, 3381, 0),new Tile(3123, 3381, 0),new Tile(3123, 3380, 0),new Tile(3124, 3380, 0),new Tile(3125, 3379, 0),new Tile(3125, 3378, 0),new Tile(3126, 3378, 0),new Tile(3127, 3377, 0),new Tile(3127, 3376, 0),new Tile(3128, 3375, 0),new Tile(3128, 3374, 0),new Tile(3128, 3373, 0),new Tile(3128, 3372, 0),new Tile(3128, 3371, 0),new Tile(3129, 3371, 0),new Tile(3129, 3370, 0),new Tile(3130, 3369, 0),new Tile(3130, 3368, 0),new Tile(3131, 3367, 0),new Tile(3131, 3366, 0),new Tile(3131, 3365, 0),new Tile(3131, 3364, 0),new Tile(3131, 3363, 0),new Tile(3131, 3362, 0),new Tile(3131, 3361, 0),new Tile(3131, 3360, 0),new Tile(3131, 3359, 0),new Tile(3131, 3358, 0),new Tile(3131, 3357, 0),new Tile(3130, 3356, 0),new Tile(3130, 3355, 0),new Tile(3130, 3354, 0),new Tile(3130, 3353, 0),new Tile(3130, 3352, 0),new Tile(3130, 3351, 0),new Tile(3130, 3350, 0),new Tile(3130, 3349, 0),new Tile(3130, 3348, 0),new Tile(3130, 3347, 0),new Tile(3129, 3346, 0),new Tile(3129, 3345, 0),new Tile(3128, 3345, 0),new Tile(3128, 3344, 0),new Tile(3128, 3343, 0),new Tile(3128, 3342, 0),new Tile(3128, 3341, 0),new Tile(3128, 3340, 0),new Tile(3128, 3339, 0),new Tile(3128, 3338, 0),new Tile(3128, 3337, 0),new Tile(3128, 3336, 0),new Tile(3128, 3335, 0),new Tile(3128, 3334, 0),new Tile(3128, 3333, 0),new Tile(3127, 3332, 0),new Tile(3126, 3331, 0),new Tile(3126, 3330, 0),new Tile(3126, 3329, 0),new Tile(3126, 3328, 0),new Tile(3126, 3327, 0),new Tile(3126, 3326, 0),new Tile(3126, 3325, 0),new Tile(3126, 3324, 0),new Tile(3126, 3323, 0),new Tile(3125, 3322, 0),new Tile(3124, 3322, 0),new Tile(3123, 3322, 0),new Tile(3122, 3321, 0),new Tile(3122, 3320, 0),new Tile(3122, 3319, 0),new Tile(3122, 3318, 0),new Tile(3122, 3317, 0),new Tile(3122, 3316, 0),new Tile(3122, 3315, 0),new Tile(3122, 3314, 0),new Tile(3122, 3313, 0),new Tile(3122, 3312, 0),new Tile(3121, 3311, 0),new Tile(3120, 3310, 0),new Tile(3119, 3309, 0),new Tile(3118, 3308, 0),new Tile(3117, 3307, 0),new Tile(3116, 3306, 0),new Tile(3116, 3305, 0),new Tile(3115, 3305, 0),new Tile(3114, 3305, 0),new Tile(3113, 3304, 0),new Tile(3113, 3303, 0),new Tile(3112, 3302, 0),new Tile(3112, 3301, 0),new Tile(3111, 3301, 0),new Tile(3111, 3300, 0),new Tile(3110, 3300, 0),new Tile(3110, 3299, 0),new Tile(3110, 3298, 0),new Tile(3110, 3297, 0),new Tile(3110, 3296, 0),new Tile(3110, 3295, 0)};

    Tile[] BarbarianVillage1ToVarrock1 = {new Tile(3131, 3318, 0),new Tile(3141, 3341, 0),new Tile(3140, 3372, 0),new Tile(3117, 3386, 0),new Tile(3104, 3410, 0),new Tile(3102, 3420, 0),new Tile(3103, 3420, 0),new Tile(3104, 3420, 0),new Tile(3105, 3420, 0),new Tile(3106, 3420, 0),new Tile(3107, 3420, 0),new Tile(3108, 3420, 0),new Tile(3109, 3420, 0),new Tile(3110, 3420, 0),new Tile(3111, 3420, 0),new Tile(3112, 3420, 0),new Tile(3113, 3420, 0),new Tile(3114, 3420, 0),new Tile(3115, 3420, 0),new Tile(3116, 3420, 0),new Tile(3117, 3420, 0),new Tile(3118, 3420, 0),new Tile(3118, 3419, 0),new Tile(3119, 3418, 0),new Tile(3120, 3418, 0),new Tile(3121, 3418, 0),new Tile(3122, 3418, 0),new Tile(3123, 3418, 0),new Tile(3124, 3418, 0),new Tile(3125, 3418, 0),new Tile(3126, 3418, 0),new Tile(3127, 3419, 0),new Tile(3128, 3420, 0),new Tile(3129, 3420, 0),new Tile(3130, 3420, 0),new Tile(3131, 3420, 0),new Tile(3132, 3420, 0),new Tile(3133, 3420, 0),new Tile(3134, 3420, 0),new Tile(3135, 3421, 0),new Tile(3136, 3421, 0),new Tile(3137, 3422, 0),new Tile(3138, 3422, 0),new Tile(3139, 3422, 0),new Tile(3140, 3422, 0),new Tile(3141, 3422, 0),new Tile(3142, 3422, 0),new Tile(3143, 3422, 0),new Tile(3144, 3421, 0),new Tile(3145, 3421, 0),new Tile(3145, 3420, 0),new Tile(3146, 3420, 0),new Tile(3146, 3419, 0),new Tile(3147, 3419, 0),new Tile(3148, 3419, 0),new Tile(3149, 3419, 0),new Tile(3150, 3419, 0),new Tile(3151, 3419, 0),new Tile(3152, 3419, 0),new Tile(3153, 3419, 0),new Tile(3154, 3418, 0),new Tile(3155, 3418, 0),new Tile(3156, 3418, 0),new Tile(3157, 3418, 0),new Tile(3158, 3419, 0),new Tile(3159, 3419, 0),new Tile(3160, 3419, 0),new Tile(3161, 3420, 0),new Tile(3162, 3421, 0),new Tile(3162, 3422, 0),new Tile(3163, 3423, 0),new Tile(3164, 3424, 0),new Tile(3165, 3425, 0),new Tile(3166, 3426, 0),new Tile(3167, 3427, 0),new Tile(3168, 3427, 0),new Tile(3169, 3427, 0),new Tile(3170, 3427, 0),new Tile(3171, 3427, 0),new Tile(3171, 3428, 0)};

    Tile[] Varrock2ToGrandExchange = {new Tile(3171, 3456, 0),new Tile(3166, 3461, 0),new Tile(3166, 3462, 0),new Tile(3166, 3463, 0),new Tile(3166, 3464, 0),new Tile(3166, 3465, 0),new Tile(3166, 3466, 0),new Tile(3166, 3467, 0),new Tile(3166, 3468, 0),new Tile(3166, 3469, 0),new Tile(3166, 3470, 0),new Tile(3166, 3471, 0),new Tile(3166, 3472, 0),new Tile(3166, 3473, 0),new Tile(3166, 3474, 0),new Tile(3166, 3475, 0),new Tile(3166, 3476, 0),new Tile(3166, 3477, 0),new Tile(3166, 3478, 0),new Tile(3166, 3479, 0),new Tile(3166, 3480, 0),new Tile(3166, 3481, 0),new Tile(3166, 3482, 0),new Tile(3166, 3483, 0),new Tile(3165, 3483, 0),new Tile(3164, 3484, 0)};
    Tile[] Varrock1To2 = {new Tile(3171, 3428, 0),new Tile(3171, 3429, 0),new Tile(3171, 3430, 0),new Tile(3171, 3431, 0),new Tile(3171, 3432, 0),new Tile(3171, 3433, 0),new Tile(3171, 3434, 0),new Tile(3171, 3435, 0),new Tile(3171, 3436, 0),new Tile(3171, 3437, 0),new Tile(3171, 3438, 0),new Tile(3171, 3439, 0),new Tile(3171, 3440, 0),new Tile(3172, 3441, 0),new Tile(3173, 3442, 0),new Tile(3174, 3443, 0),new Tile(3175, 3444, 0),new Tile(3175, 3445, 0),new Tile(3175, 3446, 0),new Tile(3175, 3447, 0),new Tile(3175, 3448, 0),new Tile(3175, 3449, 0),new Tile(3175, 3450, 0),new Tile(3175, 3451, 0),new Tile(3175, 3452, 0),new Tile(3175, 3453, 0),new Tile(3175, 3454, 0),new Tile(3175, 3455, 0),new Tile(3174, 3456, 0),new Tile(3173, 3456, 0),new Tile(3172, 3456, 0),new Tile(3171, 3456, 0),new Tile(3171, 3457, 0),new Tile(3170, 3457, 0),new Tile(3170, 3458, 0),new Tile(3169, 3458, 0),new Tile(3168, 3459, 0),new Tile(3167, 3460, 0),new Tile(3166, 3461, 0)};
    public F2PMap(ClientContext arg0) {
        super(arg0);
        ArrayList<Vertex> vertices = vertexGenerator.getVertices();
        for(int i = 0 ;i<vertices.size();i++) {
            dijkstra.addToUnvisited(vertices.get(i));
        }
    }

    public Tile [] getPath(String destinationName){
       vertexGenerator.setStartingLocation();
      vertexGenerator.setDestination(destinationName);
      Tile[] paht = dijkstra.findShortestPath(vertexGenerator.getStartingLocation(),vertexGenerator.getDestination());
       return paht;
    }

    public void map(){
        loadLumbridge();
        loadDraynorVillage();
        loadBarbarianVillage();
        loadVarrock();
        loadGrandExchange();
    }

    private void loadGrandExchange() {
        vertexGenerator.getVertex("GrandExchange");
        vertexGenerator.getVertex("GrandExchange1");
        vertexGenerator.getVertex("GrandExchange2");
        vertexGenerator.getVertex("GrandExchange3");
        vertexGenerator.getVertex("GrandExchange4");
        vertexGenerator.getVertex("GrandExchange5");
    }

    private void loadVarrock() {
        vertexGenerator.getVertex("Varrock");
        Edge Varrock1C =createEdge(10,ctx.movement.newTilePath(BarbarianVillage1ToVarrock1).reverse().toArray());
        Edge BarbarianVillage1A =createEdge(6,Varrock1To2);
        vertexGenerator.getVertex("Varrock1").addNeighbour(vertexGenerator.getVertex("Varrock2"),BarbarianVillage1A);
        vertexGenerator.getVertex("Varrock1").addNeighbour(vertexGenerator.getVertex("BarbarianVillage1"),Varrock1C);
        Edge Varrock2B =createEdge(6,ctx.movement.newTilePath(Varrock1To2).reverse().toArray());
        vertexGenerator.getVertex("Varrock2").addNeighbour(vertexGenerator.getVertex("Varrock1"),Varrock2B);
        vertexGenerator.getVertex("Varrock3");
        vertexGenerator.getVertex("Varrock4");
        vertexGenerator.getVertex("Varrock5");
    }

    private void loadBarbarianVillage() {
        vertexGenerator.getVertex("BarbarianVillage");
        Edge BarbarianVillage1A =createEdge(10,BarbarianVillage1ToVarrock1);
        Edge BarbarianVillage1B =createEdge(23,DraynorVillage1ToBarbarianVillage1);
        vertexGenerator.getVertex("BarbarianVillage1").addNeighbour(vertexGenerator.getVertex("Varrock1"),BarbarianVillage1A);
        vertexGenerator.getVertex("BarbarianVillage1").addNeighbour(vertexGenerator.getVertex("DraynorVillage1"),BarbarianVillage1B);
        vertexGenerator.getVertex("BarbarianVillage1");
        vertexGenerator.getVertex("BarbarianVillage2");
        vertexGenerator.getVertex("BarbarianVillage3");
        vertexGenerator.getVertex("BarbarianVillage4");
        vertexGenerator.getVertex("BarbarianVillage5");
    }

    private void loadDraynorVillage() {
        Edge DraynorVillageA =createEdge(4,ctx.movement.newTilePath(DraynorVillage2ToDraynorVillage).reverse().toArray());
      //  Edge DraynorVillageB =createEdge(8,LumbridgeCastleTo1);
        Edge DraynorVillageC =createEdge(7,DraynorVillageTo1);
        vertexGenerator.getVertex("DraynorVillage").addNeighbour( vertexGenerator.getVertex("DraynorVillage1"),DraynorVillageC);
       // vertexGenerator.getVertex("DraynorVillage").addNeighbour();
        vertexGenerator.getVertex("DraynorVillage").addNeighbour( vertexGenerator.getVertex("DraynorVillage2"),DraynorVillageA);
        Edge DraynorVillage1A =createEdge(7,ctx.movement.newTilePath(DraynorVillageTo1).reverse().toArray());
//        Edge DraynorVillage1B =createEdge(7,ctx.movement.newTilePath(DraynorVillageTo1).reverse().toArray());
//        Edge DraynorVillage1C =createEdge(7,ctx.movement.newTilePath(DraynorVillageTo1).reverse().toArray());
//        Edge DraynorVillage1D =createEdge(7,ctx.movement.newTilePath(DraynorVillageTo1).reverse().toArray());
        Edge DraynorVillage1E =createEdge(23,ctx.movement.newTilePath(DraynorVillage1ToBarbarianVillage1).reverse().toArray());
        vertexGenerator.getVertex("DraynorVillage1").addNeighbour( vertexGenerator.getVertex("DraynorVillage"),DraynorVillage1A);
        vertexGenerator.getVertex("DraynorVillage1").addNeighbour(vertexGenerator.getVertex("BarbarianVillage1"),DraynorVillage1E);
        Edge DraynorVillage2A =createEdge(20, LumbridgeToDraynorVillage2);
        Edge DraynorVillage2C =createEdge(4,DraynorVillage2ToDraynorVillage);
        vertexGenerator.getVertex("DraynorVillage2").addNeighbour(vertexGenerator.getVertex("Lumbridge"),DraynorVillage2A);
        vertexGenerator.getVertex("DraynorVillage2").addNeighbour(vertexGenerator.getVertex("DraynorVillage"),DraynorVillage2C);
        vertexGenerator.getVertex("DraynorVillage3");
        vertexGenerator.getVertex("DraynorVillage4");
    }

    //edge is altijd naar de vertex toe dus naar de betreffende vertex
    private void loadLumbridge(){
        Edge LumbridgeA =createEdge(3,LumbridgeCastleTo1);
        Edge LumbridgeB =createEdge(20, LumbridgeToDraynorVillage2);
       // Edge LumbridgeC =createEdge(3,LumbridgeCastleTo1);
        vertexGenerator.getVertex("Lumbridge").addNeighbour(vertexGenerator.getVertex("Lumbridge1"),LumbridgeA);
        vertexGenerator.getVertex("Lumbridge").addNeighbour(vertexGenerator.getVertex("DraynorVillage2"),LumbridgeB);
        Edge Lumbridge1A=createEdge(3,Lumbridge11To1);
        Edge Lumbridge1B =createEdge(3,ctx.movement.newTilePath(LumbridgeCastleTo1).reverse().toArray());
        Edge Lumbridge1C =createEdge(3,LumbridgeCastle1To2);
        vertexGenerator.getVertex("Lumbridge1").addNeighbour(vertexGenerator.getVertex("Lumbridge11"),Lumbridge1A);
        vertexGenerator.getVertex("Lumbridge1").addNeighbour(vertexGenerator.getVertex("Lumbridge"),Lumbridge1B);
        vertexGenerator.getVertex("Lumbridge1").addNeighbour(vertexGenerator.getVertex("Lumbridge2"),Lumbridge1C);
        Edge LumbridgeD1 = createEdge(2,Lumbridge2To3);
        Edge LumbridgeD2 = createEdge(2,ctx.movement.newTilePath(LumbridgeCastle1To2).reverse().toArray());
        Edge LumbridgeD3 = createEdge(2,ctx.movement.newTilePath(LumbridgeFToD).reverse().toArray());
        vertexGenerator.getVertex("Lumbridge2").addNeighbour( vertexGenerator.getVertex("Lumbridge3"),LumbridgeD1);
        vertexGenerator.getVertex("Lumbridge2").addNeighbour(vertexGenerator.getVertex("Lumbridge11"),LumbridgeD2);
        vertexGenerator.getVertex("Lumbridge2").addNeighbour(vertexGenerator.getVertex("Lumbridge6"),LumbridgeD3);
        Edge LumbridgeC2 = createEdge(2,ctx.movement.newTilePath(Lumbridge2To3).reverse().toArray());
        Edge LumbridgeC3 = createEdge(2,Lumbridge3To4);
        vertexGenerator.getVertex("Lumbridge3").addNeighbour(vertexGenerator.getVertex("Lumbridge2"),LumbridgeC2);
        vertexGenerator.getVertex("Lumbridge3").addNeighbour(vertexGenerator.getVertex("Lumbridge4"),LumbridgeC3);
        Edge Lumbridge4A = createEdge(2,ctx.movement.newTilePath(Lumbridge3To4).reverse().toArray());
        Edge Lumbridge4B = createEdge(2,Lumbridge4To7);
        Edge Lumbridge4C = createEdge(4,Lumbridge4To5);
        vertexGenerator.getVertex("Lumbridge4").addNeighbour(vertexGenerator.getVertex("Lumbridge3"),Lumbridge4A);
        vertexGenerator.getVertex("Lumbridge4").addNeighbour(vertexGenerator.getVertex("Lumbridge7"),Lumbridge4B);
        vertexGenerator.getVertex("Lumbridge4").addNeighbour(vertexGenerator.getVertex("Lumbridge5"),Lumbridge4C);
        Edge Lumbridge5B = createEdge(4,ctx.movement.newTilePath(Lumbridge4To5).reverse().toArray());
        vertexGenerator.getVertex("Lumbridge5").addNeighbour( vertexGenerator.getVertex("Lumbridge4"),Lumbridge5B);
        Edge LumbridgeF1 = createEdge(2,LumbridgeFToD);
        // Edge LumbridgeF2 = createEdge(9);//draynorvillage
        Edge LumbridgeF3 = createEdge(2,ctx.movement.newTilePath(LumbridgeGToF).reverse().toArray());
        vertexGenerator.getVertex("Lumbridge6").addNeighbour(vertexGenerator.getVertex("Lumbridge2"),LumbridgeF1);
        vertexGenerator.getVertex("Lumbridge6").addNeighbour(vertexGenerator.getVertex("Lumbridge7"),LumbridgeF3);
        Edge LumbridgeG1 = createEdge(2,ctx.movement.newTilePath(Lumbridge4To7).reverse().toArray());
        Edge LumbridgeG2 = createEdge(2,LumbridgeGToF);
        // Edge LumbridgeG3 = createEdge(2);//lumbridgH
        vertexGenerator.getVertex("Lumbridge7").addNeighbour(vertexGenerator.getVertex("Lumbridge4"),LumbridgeG1);
        vertexGenerator.getVertex("Lumbridge7").addNeighbour(vertexGenerator.getVertex("Lumbridge6"),LumbridgeG2);
        //Edge LumbridgeC1 = createEdge(1);//alkahid
        Edge LumbridgeE1 = createEdge(2,Lumbridge11To1);
        vertexGenerator.getVertex("Lumbridge11").addNeighbour(vertexGenerator.getVertex("Lumbridge2"),LumbridgeE1);


    }
    Edge createEdge(double cost,Tile[] path){
        Edge edge = new Edge();
        edge.setCost(cost);
        edge.setPath(path);
        return edge;
    }
}
