package lab2.firstapp.model

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromDietaryPlan(value: DietaryPlan): String {
        return value.dietaryPlan
    }

    @TypeConverter
    fun toDietaryPlan(value: String): DietaryPlan {
        return DietaryPlan.entries.firstOrNull { it.dietaryPlan == value }
            ?: throw IllegalArgumentException("Can't convert value to enum, unknown value: $value")
    }

    @TypeConverter
    fun fromFitnessPlan(value: FitnessPlan): String {
        return value.fitnessPlan
    }

    @TypeConverter
    fun toFitnessPlan(value: String): FitnessPlan {
        return FitnessPlan.entries.firstOrNull { it.fitnessPlan == value }
            ?: throw IllegalArgumentException("Can't convert value to enum, unknown value: $value")
    }

    @TypeConverter
    fun fromActivityLevel(value: ActivityLevel): String {
        return value.activityLevel
    }

    @TypeConverter
    fun toActivityLevel(value: String): ActivityLevel {
        return ActivityLevel.entries.firstOrNull { it.activityLevel == value }
            ?: throw IllegalArgumentException("Can't convert value to enum, unknown value: $value")
    }

    @TypeConverter
    fun fromGender(value: Gender): String {
        return value.gender
    }

    @TypeConverter
    fun toGender(value: String): Gender {
        return Gender.entries.firstOrNull { it.gender == value }
            ?: throw IllegalArgumentException("Can't convert value to enum, unknown value: $value")
    }
}
