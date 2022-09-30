USE banking;
delimiter //
CREATE PROCEDURE `sp_transfers_c6`(
	IN sender_id bigint(20),
	IN recipient_id bigint(20),
    IN sTransactionAmount decimal(12,0)
)
begin
declare total decimal(12,0);
declare total_sender decimal(12,0);
if(balance = (SELECT `balance` FROM `customers` WHERE `id` = sender_id) > sTransactionAmount )Then
	set recipient_id = (SELECT id  FROM `customers` WHERE `id` = recipient_id);
	set total = (SELECT balance from customers where id = recipient_id);
    set total = total + sTransactionAmount;
    UPDATE `customers` SET `balance` = total WHERE (`id` = recipient_id);
    set sender_id = (SELECT id  FROM `customers` WHERE `id` = sender_id);
    set total_sender = (SELECT balance from customers where id = sender_id);
    set total_sender = total_sender - sTransactionAmount;
    UPDATE `customers` SET `balance` = total_sender WHERE (`id` = sender_id);
    INSERT INTO `transfers` (`created_at`, `deleted`, `updated_at`, `transaction_amount`,`sender_id`,`recipient_id`) VALUES (now(), '0',now(), sTransactionAmount,sender_id,recipient_id);
    end if;
end //

SET SQL_SAFE_UPDATES = 0;