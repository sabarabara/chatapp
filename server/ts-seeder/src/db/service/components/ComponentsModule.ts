import { Module } from '@nestjs/common';
import { UserService } from './UserSeeder';
import { UserProfile } from './UserProfileSeeder';
import { RagService } from './RagSeeder';
import { ConfigModule } from 'src/db/config/ConfigModule';

@Module({
    imports: [ConfigModule],
  providers: [UserService, UserProfile, RagService, ConfigModule],
  exports: [UserService, UserProfile, RagService, ConfigModule],
})
export class ComponentsModule {}
