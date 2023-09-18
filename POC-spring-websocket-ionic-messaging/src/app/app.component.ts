import { Component } from '@angular/core';
import {MessageService} from './message.service';
import {UserToNotify} from './UserToNotify';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'websocket-frontend';
  input;
  input1;
  inputToken;
  public listUserConnected: UserToNotify[] = [];
  constructor(private messageService: MessageService) {}
  sendMessage() {
    if (this.input) {
      this.messageService.sendMessage(this.input);
      this.input = '';
    }
  }
  sendPriavteMessage() {
    if (this.input1) {
      this.messageService.sendPrivateMessage(this.input1);
      this.input1 = '';
    }
  }
  sendToken() {
   this.messageService.initializeWebSocketConnection(this.inputToken);
  }

}
