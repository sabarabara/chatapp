import { Injectable } from '@nestjs/common';
import { faker } from '@faker-js/faker';
import { DbService } from '../../config/DbService';

@Injectable()
export class UserProfile {
  constructor(private readonly db: DbService) {}

  async seed(id:string) {

    await this.db.query(
  `INSERT INTO "user_profiles" (
      user_id, blood_type, height, birthday, favorite_weather,
      favorite_color, dominant_hand, character_type, updated_at
    ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, NOW())`,
  [
    id,
    faker.helpers.arrayElement(['A', 'B', 'AB', 'O']),
    faker.number.int({ min: 150, max: 180 }),
    faker.date.past({ years: 50 }),
    faker.helpers.arrayElement(['sunny', 'rainy', 'cloudy']),
    faker.color.human(),
    faker.helpers.arrayElement(['left', 'right']),
    faker.helpers.arrayElement(['Introvert', 'Extrovert', 'Ambivert'])
  ]
);

    console.log('✅ UserProfiles seeded');
  }

  async drop() {
    await this.db.query(`DELETE FROM "user_profiles"`);
    console.log('🗑️ UserProfiles dropped');
  }
}
