import { Injectable } from '@nestjs/common';
import { faker } from '@faker-js/faker';
import { DbService } from '../../config/DbService';

@Injectable()
export class UserService {
  constructor(private readonly db: DbService) {}

  async seed(id:string) {

      await this.db.query(
        `INSERT INTO "users" (userid, username, email, created_at, last_login_at) VALUES ($1, $2, $3, NOW(), NOW())`,
        [id, faker.person.fullName(), faker.internet.email()],
      );
    console.log('✅ Users seeded');
  }

  async drop() {
    await this.db.query(`DELETE FROM "users"`);
    console.log('🗑️ Users dropped');
  }
}
