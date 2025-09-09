import { Injectable } from '@nestjs/common';
import { Seeder } from 'nestjs-seeder';
import { UserService } from './components/UserSeeder';
import { UserProfile } from './components/UserProfileSeeder';
import { RagService } from './components/RagSeeder';
import { faker } from '@faker-js/faker/locale/zu_ZA';

@Injectable()
export class SeedService implements Seeder {
  constructor(
    private readonly userService: UserService,
    private readonly userProfile: UserProfile,
    private readonly ragService: RagService,
  ) {}

  async seed() {

    for(let i=0; i<20; i++){
      const userId = faker.string.numeric(8);
      
      await this.userService.seed(userId);
      await this.userProfile.seed(userId);
      await this.ragService.seed(userId);
    }
  }

  async drop() {
    await this.ragService.drop();
    await this.userProfile.drop();
    await this.userService.drop();
  }
}
