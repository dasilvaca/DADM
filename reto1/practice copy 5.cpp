#include <bits/stdc++.h>

    static_cast
    const_cast
    reinterpret_cast
    dynamic_cast

using namespace std;

template <typename T>
class LinkedList {
 public:
  LinkedList<T> *next;
  T data;
  LinkedList() { this->next = nullptr; }
  LinkedList(T &data) {
    this->data = data;
    this->next = nullptr;
  }

  static void insertNode(LinkedList<T>*& head, T data) {
    LinkedList<T> *insertedNode = new LinkedList<T>(data);
    if (head == nullptr) {
      head = insertedNode;
      return;
    }
    LinkedList<T> *pointer = head;
    while (pointer->next != nullptr) pointer = pointer->next;
    pointer->next = insertedNode;
  }

  void printList() { 
    LinkedList *pointer = this;
    while(pointer!= nullptr){
      cout << pointer->data << ' ';
      pointer = pointer->next;
    }
    cout << '\n';
  }
};

int main() { 
  LinkedList<int> *newListHead = nullptr;
  newListHead->insertNode(newListHead,1);
  newListHead->insertNode(newListHead, 1);
  newListHead->insertNode(newListHead, 1);
  newListHead->insertNode(newListHead, 1);
  newListHead->insertNode(newListHead, 1);
  newListHead->printList();
}