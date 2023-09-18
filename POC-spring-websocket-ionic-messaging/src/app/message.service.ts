import {Injectable} from '@angular/core';
import {UserToNotify} from './UserToNotify';
import {Observable} from 'rxjs';

declare var SockJS;
declare var Stomp;

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor() {
//this.initializeWebSocketConnection('abc');

  }

  public stompClient;
  public msg = [];
  public privateMsgUser = [];
  public privateMsg = [];
  public listUserConnected: UserToNotify[] ;
  inputToken = 'abcdef123';

  initializeWebSocketConnection(tocken) {
    if (tocken) {
      const serverUrl = 'http://localhost:8082/socket?token=' + tocken;
      const ws = new SockJS(serverUrl);
      this.stompClient = Stomp.over(ws);
      const that = this;
      this.stompClient.connect({'text': 'aa'}, frame => {
        this.stompClient.send();
        that.stompClient.subscribe('/message', (message) => {
          console.log(message);
          if (message.body) {
            that.msg.push(message.body);
          }
        });
        that.stompClient.subscribe('/user/specifUser', (message) => {
          console.log(message);
          if (message.body) {
            that.privateMsgUser.push(message.body);
          }
        });
        that.stompClient.subscribe('/user/topic/privateMessage', (message) => { // any msg come from '/message' read it
          console.log(message);
          if (message.body) {
            that.privateMsg.push(message.body);
          }
        });
      });
    }
  }

  // hadhi ta9ra tab3ith l msg mil '/app/send/message' w traj3o fil '/message'
  sendMessage(message) {
    this.stompClient.send('/app/send/message', {}, message);
  }

  sendPrivateMessage(message) {
    this.stompClient.send('/app/send/privateMessage', {}, message);
  }


}
