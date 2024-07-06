package com.example.a7minworkoutapp

object Constants {
    fun defaultexerciseelist() :ArrayList<exercisemodel>{
        val exerciselist = ArrayList<exercisemodel>()

        val jumpingjack = exercisemodel(
            1,
            "Jumping Jack",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciselist.add(jumpingjack)

        val wallsit = exercisemodel(
            2,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciselist.add(wallsit)

        val pushup = exercisemodel(
            3,
            "Push UP",
            R.drawable.ic_push_up,
            false,
            false
        )
        exerciselist.add(pushup)

        val abdominalcrunch = exercisemodel(
            4,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciselist.add(abdominalcrunch)

        val stepuponchair = exercisemodel(
            5,
            "Step up onto the Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciselist.add(stepuponchair)

        val squat = exercisemodel(
            6,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciselist.add(squat)

        val tricepsdiponchair = exercisemodel(
            7,
            "Triceps dip on chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciselist.add(tricepsdiponchair)

        val plank = exercisemodel(
            8,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciselist.add(plank)

        val highkneerunning = exercisemodel(
            9,
            "High Knees running in place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciselist.add(highkneerunning)

        val lunges = exercisemodel(
            10,
            "Lunges",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciselist.add(lunges)

        val pushupandrotation = exercisemodel(
            11,
            "Push Up and Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciselist.add(pushupandrotation)

        val sideplank = exercisemodel(
            12,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        exerciselist.add(sideplank)

        return exerciselist
    }
}