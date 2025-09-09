import { Injectable } from '@nestjs/common';
import { faker } from '@faker-js/faker/locale/ja';
import { RagDbService } from '../../config/RagDBService';

@Injectable()
export class RagService {
  constructor(private readonly db: RagDbService) {}

  async seed(id:string) {
      await this.db.query(
        `INSERT INTO user_hobbies (user_id, hobby_text, embedding) VALUES ($1, $2, $3)`,
        [id, faker.lorem.paragraph(), JSON.stringify(Array.from({ length: 384 }, () => faker.number.float({ min: -1, max: 1 })))],
      );
    console.log('✅ Rags seeded');
  }

  async drop() {
    await this.db.query(`DELETE FROM user_hobbies`);
    console.log('🗑️ Rags dropped');
  }
}
